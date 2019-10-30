package com.raqun.oyster.card

import android.text.TextUtils
import com.raqun.oyster.Constants


/*
 * Checks Card number with Luhn Algorithm
 * and returns if it's valid.
 */
fun String.luhnCheck(): Boolean {
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

/*
 * Cleans Card Number
 */
fun String.cleanCardNumber(separator: Char = Constants.DEFAULT_SEPERATOR): String {
    return if (!TextUtils.isEmpty(this)) {
        replace(
            separator.toString().toRegex(), ""
        ).trim { it <= ' ' }
    } else this
}
