package com.twobuffers.nfccardreader

import android.content.Intent
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private var nfcUtils: CardNfcUtils? = null
    private val processor = Processor(
        scope = lifecycleScope,
        dispatcher = Dispatchers.IO,
        preFn = {
            lifecycleScope.launch(Dispatchers.Main) {
                Timber.d("processor> pre")
                Toast.makeText(this@MainActivity, "loading", Toast.LENGTH_SHORT).show()
            }
        },
        successFn = {cardDetails ->
            lifecycleScope.launch(Dispatchers.Main) {
                Timber.d("processor> success: $cardDetails")
                Toast.makeText(this@MainActivity, "success", Toast.LENGTH_SHORT).show()
            }
        },
        failureFn = { reason ->
            lifecycleScope.launch(Dispatchers.Main) {
                Timber.d("processor> failure: \"$reason\"")
                Toast.makeText(this@MainActivity, "failure: \"$reason\"", Toast.LENGTH_SHORT).show()
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
}
