package com.jr.mvvm_offline.data.source

import android.util.Log
import com.jr.mvvm_offline.data.source.local.dao.CryptoCurrenciesDao
import com.jr.mvvm_offline.data.source.remote.api.ApiInterface
import com.jr.mvvm_offline.data.models.CryptoCurrency
import com.jr.mvvm_offline.utils.Utils
import io.reactivex.Observable
import javax.inject.Inject

class CryptoCurrencyRepository @Inject constructor(val apiInterface: ApiInterface,
                                                   val cryptoCurrenciesDao: CryptoCurrenciesDao,
                                                   val utils: Utils) {
    fun getCryptoCurrencies(limit: Int, offset: Int): Observable<List<CryptoCurrency>> {
        var observableFromApi: Observable<List<CryptoCurrency>>? = null
        if(utils.isConnected())
            observableFromApi = getCryptoCurrenciesFromApi()
        val observableFromDb = getCryptoCurrenciesFromDb(limit, offset)

        return Observable.concatArrayEager(observableFromApi, observableFromDb)
    }

    private fun getCryptoCurrenciesFromDb(limit: Int, offset: Int): Observable<List<CryptoCurrency>> {
        return cryptoCurrenciesDao.queryCryptoCurrencies(limit, offset)
                .toObservable()
                .doOnNext {
                    Log.e("REPOSITORY DB *** ", it.size.toString())
                }
    }

    private fun getCryptoCurrenciesFromApi(): Observable<List<CryptoCurrency>> {
        return apiInterface.getCryptoCurrencies("0")
                .doOnNext {
//                    Log.e("list===", it.toString())
                    for(item in it) {
                        cryptoCurrenciesDao.insertCryptoCurrency(item)
                    }
                }
    }
}