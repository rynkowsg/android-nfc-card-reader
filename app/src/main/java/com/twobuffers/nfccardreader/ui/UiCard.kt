package com.twobuffers.nfccardreader.ui

import com.twobuffers.nfccardreader.nfc.CardDetails
import org.threeten.bp.LocalTime
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle

data class UiCard(
    val cardNumber: String,
    val expiryDate: String,
    val date: String
)

fun createUiCard(cardDetails: CardDetails, time: LocalTime = LocalTime.now()): UiCard =
    UiCard(
        cardNumber = cardDetails.cardNumber.chunked(4).joinToString(" "),
        expiryDate = "%s/%s".format(
            cardDetails.expiryDate.first.toString().padStart(2, '0'),
            cardDetails.expiryDate.second.toString()
        ),
        date = time.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM))
    )
