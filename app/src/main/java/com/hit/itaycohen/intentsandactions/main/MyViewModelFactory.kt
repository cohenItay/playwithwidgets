package com.hit.itaycohen.intentsandactions.main

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MyViewModelFactory(
    private val appContext: Context
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            MainViewModel::class.java -> modelClass.getConstructor(Context::class.java).newInstance(appContext) as T
            else -> modelClass.newInstance()
        }
    }
}
