package com.raqun.oyster.card

import com.raqun.oyster.formatter.AmexFormatter
import com.raqun.oyster.formatter.DefaultFormatter
import com.raqun.oyster.formatter.DinnersClubFormatter
import com.raqun.oyster.formatter.Formatter

sealed class CreditCard constructor(
    val id: Int,
    val formatter: Formatter,
    val validateRegex: String,
    val typeRegex: String,
    val len: Int
) {
    class Default : CreditCard(
        0,
        DefaultFormatter(),
        "^[0-9]{16}\$",
        "",
        16
    )

    class Visa : CreditCard(
        1,
        DefaultFormatter(),
        "^4[0-9]{12}(?:[0-9]{3})?\$",
        "^[4]+.*",
        16
    )

    class MasterCard : CreditCard(
        2,
        DefaultFormatter(),
        "^5[1-5][0-9]{14}\$",
        "^[5]+.*",
        16
    )

    class Amex : CreditCard(
        3,
        AmexFormatter(),
        "^3[47][0-9]{13}\$",
        "^3[47]+.*",
        16
        )

    class DinClub : CreditCard(4,
        DinnersClubFormatter(),
        )

}

