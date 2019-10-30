package com.raqun.oyster

import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import com.raqun.oyster.card.*
import com.raqun.oyster.formatter.DefaultFormatter

class Oyster private constructor(
    private val availableCards: List<CreditCard>,
    private val formatable: Boolean,
    private val validationChangeListener: ((isValid: Boolean) -> Unit)? = null,
    private val typeChangeListener: ((creditCard: CreditCard?) -> Unit)? = null
) : TextWatcher {

    private val filterArray = arrayOfNulls<InputFilter>(1)
    private var creditCard: CreditCard? = null
    private var isPatternValid = false
    private var isCardValid = false

    override fun afterTextChanged(editable: Editable?) {
        if (editable == null) return
        val s = editable.toString().cleanCardNumber()
        if (s.length >= 0) {
            if (isValidatorChanged(s)) {
                filterArray[0] = InputFilter.LengthFilter(getMaxLenForFormatter())
                editable.filters = filterArray
                typeChangeListener?.invoke(creditCard)
            }
        }

        creditCard?.let {
            isPatternValid =
                s.length >= it.len && s.matches(it.validationRegex.toRegex())

            val tempValid = validateCreditCard(s, isPatternValid)
            if (isCardValid != tempValid) {
                isCardValid = tempValid
                validationChangeListener?.invoke(isCardValid)
            }

            if (formatable) {
                it.formatter.format(editable)
            }
        }
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        // no-op
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        // no-op
    }

    private fun isValidatorChanged(s: String): Boolean {
        var isChanged = false

        if (creditCard == null) {
            creditCard = availableCards[0]
            isChanged = true
        }

        for (availableCard in availableCards) {
            if (s.matches(availableCard.typeRegex.toRegex()) && this.creditCard?.id != availableCard.id) {
                this.creditCard = availableCard
                isChanged = true
            }
        }

        return isChanged
    }

    private fun getMaxLenForFormatter(): Int {
        if (!formatable) {
            return if (creditCard is CreditCard.Visa) {
                creditCard!!.len + 3
            } else {
                creditCard!!.len
            }
        } else {
            val formatStrategy = creditCard?.formatter
            return if (formatStrategy is DefaultFormatter) {
                if (creditCard is CreditCard.Visa) {
                    creditCard!!.len + 6
                } else {
                    creditCard!!.len + 3
                }
            } else {
                creditCard!!.len + 2
            }
        }
    }

    fun validateCreditCard(carNumber: String, isPatternValid: Boolean): Boolean {
        return isPatternValid && carNumber.luhnCheck()
    }

    data class Builder(
        private var availableCards: MutableSet<CreditCard> = LinkedHashSet(),
        private var formatable: Boolean = true,
        private var validationChangeListener: ((isValid: Boolean) -> Unit)? = null,
        private var typeChangeListener: ((creditCard: CreditCard?) -> Unit)? = null
    ) {

        init {
            availableCards.add(CreditCard.UnknownCard())
        }

        fun formatable(formatable: Boolean) {
            this.formatable = formatable
        }

        fun visa() {
            availableCards.add(CreditCard.Visa())
        }

        fun master() {
            availableCards.add(CreditCard.MasterCard())
        }

        fun amex() {
            availableCards.add(CreditCard.Amex())
        }

        fun dinersClub() {
            availableCards.add(CreditCard.DinnersClub())
        }

        fun discover() {
            availableCards.add(CreditCard.Discover())
        }

        fun jcb() {
            availableCards.add(
                CreditCard.JCB(
                    REGEX_JCB,
                    TYPE_REGEX_JCB,
                    16
                )
            )

            availableCards.add(
                CreditCard.JCB(
                    REGEX_JCB,
                    TYPE_REGEX_JCB15,
                    15
                )
            )
        }

        fun onValidationChanged(validationChangeListener: ((isValid: Boolean) -> Unit)?) {
            this.validationChangeListener = validationChangeListener
        }

        fun onTypeChanged(typeChangeListener: ((creditCard: CreditCard?) -> Unit)?) {
            this.typeChangeListener = typeChangeListener
        }

        fun build(): Oyster = Oyster(
            this.availableCards.toList(),
            this.formatable,
            this.validationChangeListener,
            this.typeChangeListener
        )
    }
}