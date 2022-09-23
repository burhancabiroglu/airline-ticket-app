package com.cabir.airlineticketapp.util.extension

import androidx.lifecycle.MutableLiveData
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


fun String.parseUnicode(): String {
    val pattern = "\\u"
    val unicodeLen = 6
    val len = length - unicodeLen
    var cleanString = this
    val list = ArrayList<Pair<String,String>>()
    for (i in 0..len) {
        var buff = true
        pattern.forEachIndexed { index, c ->
            buff = buff.and((this[i+index] == c))
        }
        if(buff) list.add(Pair(substring(i,i+unicodeLen), Char(Integer.parseInt(substring(i,i+unicodeLen).removeRange(0,2),16)).toString()))
    }
    println(list)

    list.forEach {
        cleanString = cleanString.replace(it.first,it.second)
    }

    return cleanString
}

fun MutableLiveData<String>.parseUnicode(): MutableLiveData<String> {
    return MutableLiveData(value?.parseUnicode())
}



fun String.formatDate(): String {
    val locale = Locale.forLanguageTag("tr-TR")
    val parser = SimpleDateFormat("yyyy-MM-dd", locale)
    val formatter = SimpleDateFormat("dd MMM EE", locale)
    val date = parser.parse(this)
    date?.let { return formatter.format(it) }
    return ""
}

fun String.toDate(format: String = "dd.MM.yyyy"): Date? {
    val locale = Locale.forLanguageTag("tr-TR")
    val parser = SimpleDateFormat(format, locale)
    return parser.parse(this)
}