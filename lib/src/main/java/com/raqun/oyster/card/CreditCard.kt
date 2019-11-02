package com.raqun.oyster.card

import com.raqun.oyster.formatter.AmexFormatter
import com.raqun.oyster.formatter.DefaultFormatter
import com.raqun.oyster.formatter.DinnersClubFormatter
import com.raqun.oyster.formatter.Formatter

/*
 * Regex strings of Cards for validation
 */
const val REGEX_DEFAULT_CARD = "^[0-9]{16}\$"
const val REGEX_VISA = "^4[0-9]{12}(?:[0-9]{3})?\$"
const val REGEX_MASTER_CARD = "^5[1-5][0-9]{14}\$"
const val REGEX_AMEX = "^3[47][0-9]{13}\$"
const val REGEX_DINNERS_CLUB = "^3(?:0[0-5]|[68][0-9])[0-9]{11}\$"
const val REGEX_DISCOVER = "^6(?:011|5[0-9]{2})[0-9]{12}\$"
const val REGEX_JCB = "^(?:2131|1800|35\\\\d{3})\\\\d{11}\$"

/*
 * Regex strings of Cards for type recognition
 */
const val TYPE_REGEX_VISA = "^[4]+.*"
const val TYPE_REGEX_MASTER_CARD = "^[5]+.*"
const val TYPE_REGEX_AMEX = "^3[47]+.*"
const val TYPE_REGEX_DINNERS_CLUB = "^3(?:0[0-5]|[68][0-9])+.*"
const val TYPE_REGEX_DISCOVER = "^6(?:011|5[0-9]{2})+.*"
const val TYPE_REGEX_JCB = "^35\\\\d{3}+.*"
const val TYPE_REGEX_JCB15 = "^(?:2131|1800)+.*"

sealed class CreditCard constructor(
    val id: Int,
    val name: String,
    val formatter: Formatter,
    val validationRegex: String,
    val typeRegex: String,
    val len: Int
) {
    class UnknownCard : CreditCard(
        0,
        "Unknown",
        DefaultFormatter(),
        REGEX_DEFAULT_CARD,
        "",
        16
    )

    class Visa : CreditCard(
        1,
        "Visa",
        DefaultFormatter(),
        REGEX_VISA,
        TYPE_REGEX_VISA,
        13
    )

    class MasterCard : CreditCard(
        2,
        "Master Card",
        DefaultFormatter(),
        REGEX_MASTER_CARD,
        TYPE_REGEX_MASTER_CARD,
        16
    )

    class Amex : CreditCard(
        3,
        "American Express",
        AmexFormatter(),
        REGEX_AMEX,
        TYPE_REGEX_AMEX,
        15
        )

    class DinersClub : CreditCard(
        4,
        "Dinners Club",
        DinnersClubFormatter(),
        REGEX_DINNERS_CLUB,
        TYPE_REGEX_DINNERS_CLUB,
        14
        )

    class Discover : CreditCard(
        5,
        "Discover",
        DefaultFormatter(),
        REGEX_DISCOVER,
        TYPE_REGEX_DISCOVER,
        16
    )

    class JCB(
        validationRegex: String,
        typeRegex: String,
        len: Int
    ) : CreditCard(
        6,
        "JCB",
        DefaultFormatter(),
        validationRegex,
        typeRegex,
        len
    )
}



