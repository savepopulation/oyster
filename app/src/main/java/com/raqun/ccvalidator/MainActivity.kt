package com.raqun.ccvalidator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.raqun.oyster.oyster
import kotlinx.android.synthetic.main.activity_main.editTextCreditCard
import kotlinx.android.synthetic.main.activity_main.textViewCreditCardName
import kotlinx.android.synthetic.main.activity_main.textViewIsValid

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val oyster = oyster {
            visa()
            masterCard()
            mada()
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
