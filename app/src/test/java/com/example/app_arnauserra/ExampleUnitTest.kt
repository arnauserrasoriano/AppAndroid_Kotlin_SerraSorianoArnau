package com.example.app_arnauserra

import com.example.app_arnauserra.core.isPositive
import com.example.app_arnauserra.core.pricePerPerson
import com.example.app_arnauserra.core.roundTo2Decimals
import com.example.app_arnauserra.core.formatCurrency
import org.junit.Assert.*
import org.junit.Test

class ExampleUnitTest {
    @Test
    fun testIsPositive() {
        assertTrue(isPositive(5.0))
        assertFalse(isPositive(0.0))
        assertFalse(isPositive(-2.5))
    }

    @Test
    fun testPricePerPerson() {
        assertEquals(4.33, pricePerPerson(13.0, 3), 0.01)
        assertEquals(0.0, pricePerPerson(-10.0, 2), 0.0001)
        assertEquals(0.0, pricePerPerson(10.0, 0), 0.0001)
    }

    @Test
    fun testRoundTo2Decimals() {
        assertEquals(4.33, roundTo2Decimals(4.33333), 0.001)
        assertEquals(10.50, roundTo2Decimals(10.5), 0.001)
    }

    @Test
    fun testFormatCurrency() {
        assertEquals("€4,33", formatCurrency(4.33))
    }
}
