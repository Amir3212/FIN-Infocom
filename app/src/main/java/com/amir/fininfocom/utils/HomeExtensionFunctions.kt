package com.amir.fininfocom.utils

import com.amir.fininfocom.R
import com.amir.fininfocom.model.RuleIDS
import kotlin.math.sqrt

fun Int.isPrime(): Boolean {
    val number = this
    if (number <= 1) return false
    if (number == 2) return true
    if (number % 2 == 0) return false


    for (i in 3..sqrt(number.toDouble()).toInt() step 2) {
        if (number % i == 0) return false
    }

    return true
}


fun List<Int>.getFibonacciSeries(): Set<Int> {
    val numbers = this
    val fibonacciList = mutableListOf(1, 1)

    while (true) {
        val nextFibonacci =
            fibonacciList[fibonacciList.size - 1] + fibonacciList[fibonacciList.size - 2]
        if (nextFibonacci > (numbers.maxOrNull() ?: 100)) break
        fibonacciList.add(nextFibonacci)
    }

    return fibonacciList.filter { it in numbers }.toSet()
}


fun getBgColor(id: RuleIDS): Int {
    return when (id) {
        RuleIDS.EVEN -> R.color.rule_color_even
        RuleIDS.ODD -> R.color.rule_color_odd
        RuleIDS.PRIME -> R.color.rule_color_prime
        RuleIDS.FIBONACCI -> R.color.rule_color_fibonacci
        RuleIDS.PALINDROME -> R.color.rule_color_palindrome
        RuleIDS.ARMSTRONG -> R.color.rule_color_armstrong
        RuleIDS.PERFECT_NUMBER -> R.color.rule_color_perfect_number
    }
}


fun Int.isPalindrome(): Boolean {

    var originalNumber = this
    var reversedNumber = 0
    var remainder: Int

    while (originalNumber != 0) {
        remainder = originalNumber % 10
        reversedNumber = reversedNumber * 10 + remainder
        originalNumber /= 10
    }

    return this == reversedNumber
}

fun Int.isPerfectNumber(): Boolean {
    val number = this
    var sum = 1
    for (i in 2..number / 2) {
        if (number % i == 0) {
            sum += i
        }
    }
    return sum == number
}

fun Int.isArmstrong(): Boolean {
    val number = this
    val numStr = number.toString()
    val numDigits = numStr.length
    var sum = 0
    var num = number

    while (num != 0) {
        val digit = num % 10
        sum += Math.pow(digit.toDouble(), numDigits.toDouble()).toInt()
        num /= 10
    }

    return sum == number
}