package com.example.app_arnauserra.core

import kotlin.math.round

// Checks if a decimal number is strictly positive (greater than 0).
fun isPositive(x: Double): Boolean = x > 0.0

// Calculates the price per person given the total and the number of people.
// Returns 0.0 if the total is negative or if the number of people is less than or equal to 0.
fun pricePerPerson(total: Double, people: Int): Double {
    if (total < 0 || people <= 0) return 0.0
    return total / people
}

// Rounds a decimal number to 2 exact decimal places.
fun roundTo2Decimals(x: Double): Double = round(x * 100.0) / 100.0

// Formats a decimal value into a currency string format (€).
fun formatCurrency(x: Double): String = String.format("€%.2f", roundTo2Decimals(x))

