package com.twobuffers.nfccardreader.ui

import android.content.Intent
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.twobuffers.nfccardreader.R
import com.twobuffers.nfccardreader.nfc.CardNfcUtils
import com.twobuffers.nfccardreader.nfc.Processor
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private var nfcUtils: CardNfcUtils? = null

    private val list: MutableList<UiCard> = mutableListOf()

    private val adapter = MyAdapter(list)
    private val processor = initProcessor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list_rv.layoutManager = LinearLayoutManager(this)
        list_rv.adapter = adapter

        nfcUtils = CardNfcUtils(this)

        // Read card on launch
        if (intent.action === NfcAdapter.ACTION_TECH_DISCOVERED) {
            onNewIntent(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        nfcUtils?.enableDispatch()
    }

    override fun onPause() {
        super.onPause()
        nfcUtils?.disableDispatch()
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        Timber.d("New event: $intent, ${intent.action}, ${intent.categories}, ${intent.getParcelableExtra<Tag>(NfcAdapter.EXTRA_TAG)}")
        lifecycleScope.launch {
            processor.execute(intent)
        }
    }

    private fun initProcessor() = Processor(
        scope = lifecycleScope,
        dispatcher = Dispatchers.IO,
        preFn = {
            lifecycleScope.launch(Dispatchers.Main) {
                Timber.d("processor> pre")
                Toast.makeText(this@MainActivity, "loading", Toast.LENGTH_SHORT).show()
            }
        },
        successFn = { cardDetails ->
            lifecycleScope.launch(Dispatchers.Main) {
                Timber.d("processor> success: $cardDetails")
                Toast.makeText(this@MainActivity, "success", Toast.LENGTH_SHORT).show()
                if (cardDetails == null) {
                    Timber.w("cardDetails == null")
                    return@launch
                }
                list.add(0, createUiCard(cardDetails))
                adapter.set(list)
            }
        },
        failureFn = { reason ->
            lifecycleScope.launch(Dispatchers.Main) {
                Timber.d("processor> failure: \"$reason\"")
                Toast.makeText(this@MainActivity, "failure: \"$reason\"", Toast.LENGTH_SHORT).show()
            }
        }
    )
}
