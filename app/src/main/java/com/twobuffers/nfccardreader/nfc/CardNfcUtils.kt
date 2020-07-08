package com.twobuffers.nfccardreader.nfc

import android.app.Activity
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.nfc.NfcAdapter
import android.nfc.tech.IsoDep
import timber.log.Timber

class CardNfcUtils(private val mActivity: Activity) {

    private var mNfcAdapter: NfcAdapter? = NfcAdapter.getDefaultAdapter(mActivity)
    private val mPendingIntent: PendingIntent = PendingIntent.getActivity(
        mActivity,
        0,
        Intent(mActivity, mActivity.javaClass).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP),
        0
    )

    fun enableDispatch() {
        mNfcAdapter?.enableForegroundDispatch(
            mActivity,
            mPendingIntent,
            INTENT_FILTER,
            TECH_LIST
        )
        Timber.d("dispatch ENABLED")
    }

    fun disableDispatch() {
        mNfcAdapter?.disableForegroundDispatch(mActivity)
        Timber.d("dispatch DISABLED")
    }

    companion object {
        private val INTENT_FILTER = arrayOf(
            IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED),
            IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED)
        )
        private val TECH_LIST =
            arrayOf(arrayOf(IsoDep::class.java.name))
    }
}

fun isNfcAvailable(context: Context): Boolean {
    NfcAdapter.getDefaultAdapter(context) ?: return false
    return true
}

fun isNfcEnabled(context: Context): Boolean {
    val adapter = NfcAdapter.getDefaultAdapter(context) ?: return false
    return adapter.isEnabled
}
