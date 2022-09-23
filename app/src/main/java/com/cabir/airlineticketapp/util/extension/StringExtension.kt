package com.cabir.airlineticketapp.util.extension

import java.text.NumberFormat
import java.util.*


fun String.parseUnicode(): String {
    val arr = split("\\u")
    if(arr.isEmpty() || arr.count() < 2) return this
    val str = arr[1]
    var unicode = ""
    var startPoint: Int? = null
    var endPoint: Int? = null
    str.forEachIndexed { index, c ->
        if(c.isDigit()) {
            if(unicode.isEmpty()) startPoint = index
            else endPoint = index+1
            unicode = unicode.plus(c.toString())
        }
    }
    val c = Char(Integer.parseInt(unicode,16))
    var cleanString = str
    if(startPoint!=null && endPoint!= null)
        cleanString = c.toString()+str.removeRange(startPoint!!, endPoint!!)
    return cleanString
}
