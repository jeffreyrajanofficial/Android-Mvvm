package com.jr.mvvm_offline.ui.list

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject

class CryptoCurrenciesViewModelFactory @Inject constructor(
        private val cryptoCurrenciesViewModel: CryptoCurrenciesViewModel
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(CryptoCurrenciesViewModel::class.java)) {
         return cryptoCurrenciesViewModel as T
        }

        throw IllegalArgumentException("Unknown class name")
    }

}