package com.twobuffers.nfccardreader

import android.content.Intent
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.IsoDep
import com.github.devnied.emvnfccard.model.EmvCard
import com.github.devnied.emvnfccard.parser.EmvTemplate
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.plus
import kotlinx.coroutines.withContext
import timber.log.Timber
import org.threeten.bp.Instant;
import org.threeten.bp.ZoneId;

data class CardDetails(
    val cardNumber: String,
    val expiryDate: Pair<Int, Int>
)

class Processor(
    scope: CoroutineScope,
    dispatcher: CoroutineDispatcher,
    private val preFn: suspend () -> Unit = {},
    private val successFn: suspend (cardDetails: CardDetails?) -> Unit = {},
    private val failureFn: suspend (reason: String) -> Unit = {}
) {
    private val localScope = scope + dispatcher
    private val provider = Provider()

    suspend fun execute(intent: Intent) {
        try {
            preFn()
            Timber.d("(@try)")
            val cardDetails = withContext(localScope.coroutineContext) { internalExecute(intent) }
            successFn(cardDetails)
        } catch (t: Throwable) {
            Timber.d("(@catch) t=$t")
            failureFn("caught exception: ${t.localizedMessage}")
        } finally {
            Timber.d("(@finally)")
        }
    }

    private suspend fun internalExecute(intent: Intent): CardDetails? {
        val tag = intent.getParcelableExtra<Tag>(NfcAdapter.EXTRA_TAG)
        if (tag == null) {
            Timber.d("tag==null")
            return null
        }

        val isoDep = IsoDep.get(tag)
        if (isoDep == null) {
            failureFn("isoDep == null")
            return null
        }

        try {
            isoDep.connect()
            val lastAts = getAts(isoDep)
            Timber.d("lastAts: ${lastAts?.toList().toString()}") // [FOR MONZO] lastAts: [115, -56, 64, 0, 0, -112, 0]
            // which is in hex: 73 C8 40 00 00 90

            provider.setmTagCom(isoDep)

            val config: EmvTemplate.Config = EmvTemplate.Config()
                .setContactLess(true) // Enable contact less reading (default: true)
                .setReadAllAids(true) // Read all aids in card (default: true)
                .setReadTransactions(true) // Read all transactions (default: true)
                .setReadCplc(true) // Read and extract CPCLC data (default: false)
                .setRemoveDefaultParsers(false) // Remove default parsers for GeldKarte and EmvCard (default: false)
                .setReadAt(true) // Read and extract ATR/ATS and description

                // Create Parser
            val parser = EmvTemplate.Builder() //
                .setProvider(provider) // Define provider
                .setConfig(config) // Define config
                //.setTerminal(terminal) (optional) you can define a custom terminal implementation to create APDU
                .build()

            // Read card
            val card: EmvCard = parser.readEmvCard()
            Timber.d("card: $card")
            val expireDate = Instant.ofEpochMilli(card.expireDate.time)
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
            return CardDetails(
                cardNumber = card.cardNumber,
                expiryDate = Pair(expireDate.monthValue, expireDate.year)
            )
        } catch (t: Throwable) {
            failureFn("t2: ${t.localizedMessage}")
        }
        return null
    }
}

private fun getAts(pIso: IsoDep): ByteArray? {
    var ret: ByteArray? = null
    if (pIso.isConnected) {
        // Extract ATS from NFC-A
        ret = pIso.historicalBytes
        if (ret == null) {
            // Extract ATS from NFC-B
            ret = pIso.hiLayerResponse
        }
    }
    return ret
}
