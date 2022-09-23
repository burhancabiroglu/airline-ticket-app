package com.cabir.airlineticketapp

import com.cabir.airlineticketapp.util.extension.parseUnicode
import org.junit.Test

import org.junit.Assert.*

class UnitTest {
    @Test
    fun unicodeSingleParser_isCorrect() {
        assertEquals("Türk" ,"T\\u00fcrk".parseUnicode())
    }

    @Test
    fun unicodeParser_isCorrect() {
        val unicodeString =  "T\\u00fcrk Hava Yollar\\u0131"
        assertEquals("Türk Hava Yolları" ,unicodeString.parseUnicode())
    }
}