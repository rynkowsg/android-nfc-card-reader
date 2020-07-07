package com.twobuffers.nfccardreader

import android.annotation.SuppressLint
import android.nfc.tech.IsoDep
import com.github.devnied.emvnfccard.enums.SwEnum
import com.github.devnied.emvnfccard.exception.CommunicationException
import com.github.devnied.emvnfccard.parser.IProvider
import com.github.devnied.emvnfccard.utils.TlvUtil
import fr.devnied.bitlib.BytesUtils
import timber.log.Timber
import java.io.IOException

/**
 * Provider used to communicate with EMV card
 *
 * @author Millau Julien
 */
class Provider : IProvider {

    /**
     * CardChanel
     */
//    private val channel: CardChannel? = null

    /**
     * Method used to get the field log
     *
     * @return the log
     */
    /**
     * Logger
     */
    val log = StringBuffer()

    /**
     * Tag comm
     */
    private var mTagCom: IsoDep? = null

    @SuppressLint("BinaryOperationInTimber")
    @Throws(CommunicationException::class)
    override fun transceive(pCommand: ByteArray): ByteArray {
        log.append("============================================================\n")
        log.append("send: " + BytesUtils.bytesToString(pCommand))
            .append("\n")
        var response: ByteArray? = null
        response = try {
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
        Timber.d("[log] ${log.toString()}")
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