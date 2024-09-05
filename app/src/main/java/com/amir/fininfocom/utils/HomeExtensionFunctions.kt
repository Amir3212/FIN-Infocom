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
    }
}