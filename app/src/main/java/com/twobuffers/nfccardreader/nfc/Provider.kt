package com.twobuffers.nfccardreader.nfc

import android.annotation.SuppressLint
import android.nfc.tech.IsoDep
import com.github.devnied.emvnfccard.enums.SwEnum
import com.github.devnied.emvnfccard.exception.CommunicationException
import com.github.devnied.emvnfccard.parser.IProvider
import com.github.devnied.emvnfccard.utils.TlvUtil
import fr.devnied.bitlib.BytesUtils
import timber.log.Timber
import java.io.IOException

class Provider : IProvider {
    val log = StringBuffer()
    private var mTagCom: IsoDep? = null

    @SuppressLint("BinaryOperationInTimber")
    @Throws(CommunicationException::class)
    override fun transceive(pCommand: ByteArray): ByteArray? {
        log.append("============================================================\n")
        log.append("send: " + BytesUtils.bytesToString(pCommand))
            .append("\n")
        val response: ByteArray? = try {
            // send command to emv card
            mTagCom!!.transceive(pCommand)
        } catch (e: IOException) {
            throw CommunicationException(e.message)
        }
        log.append("resp: " + BytesUtils.bytesToString(response)).append("\n")
        try {
            val value = SwEnum.getSW(response)
            if (value != null) {
                log.append("resp: " + value.detail)
            }
            log.append(TlvUtil.prettyPrintAPDUResponse(response)).append("\n")
        } catch (e: Exception) {
        }
        Timber.d(log.toString())
        return response
    }

    override fun getAt(): ByteArray? {
        return arrayOf(1.toByte(), 2.toByte(), 3.toByte()).toByteArray()
    }

    /**
     * Setter for the field mTagCom
     *
     * @param mTagCom
     * the mTagCom to set
     */
    fun setmTagCom(mTagCom: IsoDep?) {
        this.mTagCom = mTagCom
    }
}
