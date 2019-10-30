package com.raqun.oyster.card

/*
 * Checks Card number with Luhm Algorithm
 * and returns if it's valid.
 */
fun String.luhmCheck(): Boolean {
    var sum = 0
    var alternate = false
    for (i in length - 1 downTo 0) {
        var n = Integer.parseInt(substring(i, i + 1))
        if (alternate) {
            n *= 2
            if (n > 9) {
                n = n % 10 + 1
            }
        }
        sum += n
        alternate = !alternate
    }
    return sum % 10 == 0
}