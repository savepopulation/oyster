package com.raqun.oyster.formatter

import android.text.Editable
import android.text.TextUtils
import com.raqun.oyster.Constants


class DinnersClubFormatter(private val separator: Char = Constants.DEFAULT_SEPERATOR) : Formatter {
    override fun format(editable: Editable) {
        if (editable.length == 5 || editable.length == 12) {
            val c = editable[editable.length - 1]
            if (separator == c) {
                editable.delete(editable.length - 1, editable.length)
            } else if (Character.isDigit(c) && TextUtils.split(
                    editable.toString(),
                    separator.toString()
                ).size <= 3
            ) {
                editable.insert(
                    editable.length - 1,
                    separator.toString()
                )
            }
        }
    }
}