package com.cabir.airlineticketapp.util.extension

import java.text.SimpleDateFormat
import java.util.*


fun String.formatDate(): String {
    val locale = Locale.forLanguageTag("tr-TR")
    val parser = SimpleDateFormat("yyyy-MM-dd", locale)
    val formatter = SimpleDateFormat("dd MMM EE", locale)
    val date = parser.parse(this)
    date?.let { return formatter.format(it) }
    return ""
}

fun String.toDate(strategy: DateStrategy = DateStrategy.FORMAT1): Date? {
    if(strategy.format.isBlank()) return null
    val locale = Locale.forLanguageTag("tr-TR")
    val parser = SimpleDateFormat(strategy.format, locale)
    return parser.parse(this)
}

enum class DateStrategy(val format: String) {
    FORMAT1( "dd.MM.yyyy"),
    FORMAT2( "yyyy-MM-dd"),
}