package com.jr.mvvm_offline.ui.list

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.jr.mvvm_offline.data.source.CryptoCurrencyRepository
import com.jr.mvvm_offline.data.models.CryptoCurrency
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CryptoCurrenciesViewModel @Inject constructor(
        private val cryptoCurrencyRepository: CryptoCurrencyRepository
): ViewModel() {

    var cryptoCurrenciesResult: MutableLiveData<List<CryptoCurrency>> = MutableLiveData()
    var cryptoCurrenciesError: MutableLiveData<String> = MutableLiveData()
    var cryptoCurrenciesLoader: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var disposableObserver: DisposableObserver<List<CryptoCurrency>>

    fun cryptoCurrenciesResult(): LiveData<List<CryptoCurrency>> = cryptoCurrenciesResult

    fun cryptoCurrenciesError(): LiveData<String> = cryptoCurrenciesError

    fun cryptoCurrencyLoader(): LiveData<Boolean> = cryptoCurrenciesLoader

    fun loadCurrencies(limit: Int, offset: Int) {
        disposableObserver = object : DisposableObserver<List<CryptoCurrency>> () {
            override fun onComplete() {

            }

            override fun onNext(t: List<CryptoCurrency>) {
                cryptoCurrenciesResult.postValue(t)
                cryptoCurrenciesLoader.postValue(false)
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
                cryptoCurrenciesError.postValue(e.message)
                cryptoCurrenciesLoader.postValue(false)
            }

        }

        cryptoCurrencyRepository.getCryptoCurrencies(limit, offset)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .debounce(400, TimeUnit.MILLISECONDS)
                .subscribe(disposableObserver)
    }

    fun disposeElements() {
        if(!disposableObserver.isDisposed)
            disposableObserver.dispose()
    }
}