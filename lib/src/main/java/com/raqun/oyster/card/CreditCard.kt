package com.raqun.oyster.card

import com.raqun.oyster.R
import com.raqun.oyster.formatter.AmexFormatter
import com.raqun.oyster.formatter.DefaultFormatter
import com.raqun.oyster.formatter.DinersClubFormatter
import com.raqun.oyster.formatter.Formatter

/*
 * Regex strings of Cards for validation
 */
const val REGEX_DEFAULT_CARD = "^[0-9]{16}\$"
const val REGEX_VISA = "^4[0-9]{12}(?:[0-9]{3})?\$"
const val REGEX_MASTER_CARD = "^5[1-5][0-9]{14}\$"
const val REGEX_AMEX = "^3[47][0-9]{13}\$"
const val REGEX_DINERS_CLUB = "^3(?:0[0-5]|[68][0-9])[0-9]{11}\$"
const val REGEX_DISCOVER = "^6(?:011|5[0-9]{2})[0-9]{12}\$"
const val REGEX_JCB = "^(?:2131|1800|35\\\\d{3})\\\\d{11}\$"
const val REGEX_MADA =
    "^(4(0(0861|1757|7(197|395)|9201)|1(0685|7633|9593)|2(281(7|8|9)|8(331|67(1|2|3)))|3(1361|2328|4107|9954)|4(0(533|647|795)|5564|6(393|404|672))|5(5(036|708)|7865|8456)|6(2220|854(0|1|2|3))|8(301(0|1|2)|4783|609(4|5|6)|931(7|8|9))|93428)|5(0(4300|8160)|13213|2(1076|4(130|514)|9(415|741))|3(0906|1095|2013|5(825|989)|6023|7767|9931)|4(3(085|357)|9760)|5(4180|7606|8848)|8(5265|8(8(4(5|6|7|8|9)|5(0|1))|98(2|3))|9(005|206)))|6(0(4906|5141)|36120)|9682(0(1|2|3|4|5|6|7|8|9)|1(0|1)))\\d{10}\$"

/*
 * Regex strings of Cards for type recognition
 */
const val TYPE_REGEX_VISA = "^[4]+.*"
const val TYPE_REGEX_MASTER_CARD = "^[5]+.*"
const val TYPE_REGEX_AMEX = "^3[47]+.*"
const val TYPE_REGEX_DINERS_CLUB = "^3(?:0[0-5]|[68][0-9])+.*"
const val TYPE_REGEX_DISCOVER = "^6(?:011|5[0-9]{2})+.*"
const val TYPE_REGEX_JCB = "^35\\\\d{3}+.*"
const val TYPE_REGEX_JCB15 = "^(?:2131|1800)+.*"
const val TYPE_REGEX_MADA =
    "^(58845|440647|440795|410621|420132|457997|474491|558563|446404|457865|968208|636120|417633|468540|468541|468542|468543|968201|446393|409201|458456|484783|968205|462220|455708|588848|455036|968203|486094|486095|486096|504300|440533|489318|489319|445564|968211|410685|406996|432328|428671|428672|428673|968206|446672|543357|434107|407197|407395|412565|431361|604906|521076|588850|968202|529415|535825|543085|524130|554180|549760|968209|524514|529741|537767|535989|536023|513213|520058|585265|588983|588982|589005|508160|531095|530906|532013|605141|968204|422817|422818|422819|428331|483010|483011|483012|589206|968207|419593|439954|423117|445682|465002|530060|531196)+.*"


sealed class CreditCard constructor(
    val id: Int,
    val name: String,
    val formatter: Formatter,
    val validationRegex: String,
    val typeRegex: String,
    val len: Int,
    val logo: Int?,
) {
    class UnknownCard : CreditCard(
        0,
        "Unknown",
        DefaultFormatter(),
        REGEX_DEFAULT_CARD,
        "",
        16,
        null,
    )

    class Visa : CreditCard(
        1,
        "Visa",
        DefaultFormatter(),
        REGEX_VISA,
        TYPE_REGEX_VISA,
        13,
        R.drawable.ic_visa,
    )

    class MasterCard : CreditCard(
        2,
        "Master Card",
        DefaultFormatter(),
        REGEX_MASTER_CARD,
        TYPE_REGEX_MASTER_CARD,
        16,
        R.drawable.ic_mastercard
    )

    class Amex : CreditCard(
        3,
        "American Express",
        AmexFormatter(),
        REGEX_AMEX,
        TYPE_REGEX_AMEX,
        15,
        R.drawable.ic_amex,
    )

    class DinersClub : CreditCard(
        4,
        "Diners Club",
        DinersClubFormatter(),
        REGEX_DINERS_CLUB,
        TYPE_REGEX_DINERS_CLUB,
        14,
        R.drawable.ic_diners,
    )

    class Discover : CreditCard(
        5,
        "Discover",
        DefaultFormatter(),
        REGEX_DISCOVER,
        TYPE_REGEX_DISCOVER,
        16,
        R.drawable.ic_discover,
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
        len,
        R.drawable.ic_jcb,
    )

    class MADA : CreditCard(
        7,
        "MADA",
        DefaultFormatter(),
        REGEX_MADA,
        TYPE_REGEX_MADA,
        16,
        R.drawable.ic_mada,
    )
}



