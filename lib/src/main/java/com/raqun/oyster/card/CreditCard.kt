package com.raqun.oyster.card

import com.raqun.oyster.formatter.AmexFormatter
import com.raqun.oyster.formatter.DefaultFormatter
import com.raqun.oyster.formatter.DinnersClubFormatter
import com.raqun.oyster.formatter.Formatter

sealed class CreditCard constructor(
    val id: Int,
    val formatter: Formatter,
    val validationRegex: String,
    val typeRegex: String,
    val len: Int
) {
    class UnknownCard : CreditCard(
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
        13
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
        15
        )

    class DinClub : CreditCard(4,
        DinnersClubFormatter(),
        "^3(?:0[0-5]|[68][0-9])[0-9]{11}\$",
        "^3(?:0[0-5]|[68][0-9])+.*",
        14
        )

    class Discover : CreditCard(
        5,
        DefaultFormatter(),
        "^6(?:011|5[0-9]{2})[0-9]{12}\$",
        "^6(?:011|5[0-9]{2})+.*",
        16
    )

    class JCB(
        validationRegex: String,
        typeRegex: String,
        len: Int
    ) : CreditCard(
        6,
        DefaultFormatter(),
        validationRegex,
        typeRegex,
        len
    )

}

