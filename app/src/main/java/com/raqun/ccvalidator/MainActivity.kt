package com.raqun.ccvalidator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.raqun.oyster.CreditCardValidator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val oyster = CreditCardValidator.build {
            visa()
            masterCard()
            onTypeChanged {
                textViewCreditCardName.text = it?.name
            }
            onValidationChanged {
                textViewIsValid.text = it.toString()
            }
        }

        editTextCreditCard.addTextChangedListener(oyster)
    }
}
