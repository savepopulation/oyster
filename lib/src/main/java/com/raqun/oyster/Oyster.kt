package com.raqun.oyster

import android.text.Editable
import android.text.TextWatcher
import com.raqun.oyster.card.CreditCard

class Oyster private constructor(
    private val availableCards: List<CreditCard>,
    private val formatable: Boolean,
    private val validationPoint: Int,
    private val validationChangeListener: ((isValid: Boolean) -> Unit)? = null,
    private val typeChangeListener: ((creditCard: CreditCard) -> Unit)? = null
) : TextWatcher {


    override fun afterTextChanged(p0: Editable?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    data class Builder(
        private var availableCards: MutableSet<CreditCard> = LinkedHashSet(),
        private var formatable: Boolean = true,
        private var validationPoint: Int,
        private var validationChangeListener: ((isValid: Boolean) -> Unit)? = null,
        private var typeChangeListener: ((creditCard: CreditCard) -> Unit)? = null
    ) {

        init {
            availableCards.add(CreditCard.UnknownCard())
            this.validationPoint = 4
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
                    "^(?:2131|1800|35\\\\d{3})\\\\d{11}\$",
                    "^35\\\\d{3}+.*",
                    16
                )
            )

            availableCards.add(
                CreditCard.JCB(
                    "^(?:2131|1800|35\\\\d{3})\\\\d{11}\$",
                    "^(?:2131|1800)+.*",
                    15
                )
            )

            this.validationPoint = 5
        }

        fun onValidationChanged(validationChangeListener: ((isValid: Boolean) -> Unit)?) {
            this.validationChangeListener = validationChangeListener
        }

        fun onTypeChanged(typeChangeListener: ((creditCard: CreditCard) -> Unit)?) {
            this.typeChangeListener = typeChangeListener
        }

        fun build(): Oyster = Oyster(
            this.availableCards.toList(),
            this.formatable,
            this.validationPoint,
            this.validationChangeListener,
            this.typeChangeListener
        )
    }
}