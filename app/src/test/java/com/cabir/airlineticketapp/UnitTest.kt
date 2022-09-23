package com.cabir.airlineticketapp

import com.cabir.airlineticketapp.util.extension.parseUnicode
import org.junit.Test

import org.junit.Assert.*

class UnitTest {
    @Test
    fun unicodeParser_isCorrect() {
        val unicodeString =  "\\u0130stanbul"
        assertEquals("İstanbul" ,unicodeString.parseUnicode())
    }
}