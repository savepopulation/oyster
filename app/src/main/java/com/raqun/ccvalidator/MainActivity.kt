package com.raqun.ccvalidator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.raqun.oyster.oyster
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val oyster = oyster {
            visa()
            masterCard()
            mada()
            onTypeChanged { creditCard ->
                textViewCreditCardName.text = creditCard?.name
                val logoDrawable = creditCard?.logo?.let { ContextCompat.getDrawable(this@MainActivity, it) }
                imageViewCardLogo.setImageDrawable(logoDrawable)
            }
            onValidationChanged {
                textViewIsValid.text = it.toString()
            }
        }

        editTextCreditCard.addTextChangedListener(oyster)
    }
}
