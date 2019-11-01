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

### LICENSE

MIT License

Copyright (c) 2019 Taylan Sabırcan

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

