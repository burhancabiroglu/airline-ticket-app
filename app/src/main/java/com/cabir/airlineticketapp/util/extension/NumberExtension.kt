package com.cabir.airlineticketapp.util.extension

import java.text.NumberFormat
import java.util.*


fun Int.toNumberFormat(): String {
    val formatter = NumberFormat.getNumberInstance()
    val parsed = formatter.parse(this.toString())
    val formatted = formatter.format(parsed)
    return formatted.replace(",",".")
}


fun Double.toCurrencyFormat(locale: Locale = Locale.forLanguageTag("tr-TR")): String {
    val formatter = NumberFormat.getCurrencyInstance(locale)
    val formatted = formatter.format(this)
    return formatted.removeRange(0,1)
}