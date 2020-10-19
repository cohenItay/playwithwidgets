package com.hit.itaycohen.intentsandactions.main

import android.content.Context
import android.text.Editable
import androidx.lifecycle.ViewModel
import com.hit.itaycohen.intentsandactions.R

class MainViewModel(
    private val appContext: Context
) : ViewModel() {

    private var initialWelcome = appContext.getString(R.string.welcome_message)
    var inputAmount: Int = 0
        private set

    fun getLabelTextFor(editable: Editable, amount: Int?): CharSequence? {
        val tempAmount = amount ?: 0
        inputAmount = tempAmount
        return when {
            editable.isEmpty() -> initialWelcome
            tempAmount > 20 -> appContext.getString(R.string.natanItsTooMuch)
            else -> appContext.getString(R.string.fireOnYou)
        }
    }
}