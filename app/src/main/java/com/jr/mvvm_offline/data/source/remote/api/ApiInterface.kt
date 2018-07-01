package com.jr.mvvm_offline.data.source.remote.api


import com.jr.mvvm_offline.data.models.CryptoCurrency
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("ticker/")
    fun getCryptoCurrencies(@Query("start") start: String): Observable<List<CryptoCurrency>>
}