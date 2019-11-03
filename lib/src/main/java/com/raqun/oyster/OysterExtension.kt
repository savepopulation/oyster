package com.raqun.oyster

fun oyster(block: CreditCardValidator.Builder.() -> Unit) =
    CreditCardValidator.Builder().apply(block).build()
