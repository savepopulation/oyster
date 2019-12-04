package com.raqun.oyster.card

class CardUtil {

    companion object {
        fun getType(cardNumber: String): CardType {
            val regexMap = mapOf(
                TYPE_REGEX_VISA to CardType.VISA,
                TYPE_REGEX_MASTER_CARD to CardType.MASTER_CARD,
                TYPE_REGEX_AMEX to CardType.AMERICAN_EXPRESS,
                TYPE_REGEX_DINERS_CLUB to CardType.DINERS_CLUB,
                TYPE_REGEX_DISCOVER to CardType.DISCOVER,
                TYPE_REGEX_JCB to CardType.JCB,
                TYPE_REGEX_JCB15 to CardType.JCB15
            )
            for (regex in regexMap) {
                if (cardNumber.cleanCardNumber().matches(regex.key.toRegex())) {
                    return regex.value
                }
            }
            return CardType.UNKNOWN
        }
    }
}