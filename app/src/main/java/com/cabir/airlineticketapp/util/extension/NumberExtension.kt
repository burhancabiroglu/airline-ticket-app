package com.cabir.airlineticketapp.util.extension

import java.text.NumberFormat
import java.util.*


fun String.toCurrencyFormat(locale: Locale = Locale.forLanguageTag("tr-TR")): String? {
    val formatter = NumberFormat.getCurrencyInstance(locale)
    return formatter.format(this)
}

fun String.toNumberFormat(): String {
    val formatter = NumberFormat.getNumberInstance()
    val parsed = formatter.parse(this)
    return formatter.format(parsed)
}

fun Double.toNumberFormat(): String {
    val formatter = NumberFormat.getNumberInstance()
    val parsed = formatter.parse(this.toString())
    return formatter.format(parsed)
}

fun Int.toNumberFormat(): String {
    val formatter = NumberFormat.getNumberInstance()
    val parsed = formatter.parse(this.toString())
    return formatter.format(parsed).apply {
        replace(",",".")
    }
}