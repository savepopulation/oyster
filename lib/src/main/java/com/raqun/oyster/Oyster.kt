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
        private var formatable: Boolean,
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

        fun onValidationChanged(validationChangeListener: ((isValid: Boolean) -> Unit)?) {
            this.validationChangeListener = validationChangeListener
        }

        fun onTypeChanged(typeChangeListener: ((creditCard: CreditCard) -> Unit)?) {
            this.typeChangeListener = typeChangeListener
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

        fun dinClub() {
            availableCards.add(CreditCard.DinClub())
        }

        fun discover() {
            availableCards.add(CreditCard.Discover())
        }
    }


}