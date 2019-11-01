# oyster
A lightweight Credit Card number format, validation and type recognition library.

### How to use?
```
val oyster = CreditCardValidator.Builder()
            // Add the credit cards you want to support
            .visa()
            .masterCard()
            // Listen card type and type changes
            .onTypeChanged {
                textViewCreditCardName.text = it?.name
            }
            // Listen validation changes
            .onValidationChanged {
                textViewIsValid.text = it.toString()
            }
            .build()

// Finally add oyster to your EditText as TextChangedListener
editTextCreditCard.addTextChangedListener(oyster)

```

### Supported Credit Card Types:
    * Visa
    * Master Card
    * American Express
    * Dinners Club
    * Discover
    * JCB

### Dependency<br>
```
maven { url 'https://jitpack.io' }
```
```
dependencies {
    implementation 'com.github.savepopulation:oyster:1.0.0'
}
``` 

### Apps using in production
Please feel free to email me if you use oyster in production.

### Where "Oyster" comes from?
It comes from nowhere. :)


