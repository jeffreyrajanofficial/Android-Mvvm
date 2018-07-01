package com.jr.mvvm_offline.data.source.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.jr.mvvm_offline.data.models.CryptoCurrency
import io.reactivex.Single

@Dao
interface CryptoCurrenciesDao {

    @Query("SELECT * FROM cryptocurrencies ORDER BY rank limit :limit offset :offset")
    fun queryCryptoCurrencies(limit: Int, offset: Int): Single<List<CryptoCurrency>>

    @Insert(
            onConflict = OnConflictStrategy.REPLACE
    )
    fun insertCryptoCurrency(cryptoCurrency: CryptoCurrency)
}