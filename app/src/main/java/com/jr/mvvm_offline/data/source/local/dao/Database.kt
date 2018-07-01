package com.jr.mvvm_offline.data.source.local.dao

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.jr.mvvm_offline.data.models.CryptoCurrency

@Database(entities = [(CryptoCurrency::class)], version = 3)
abstract class Database: RoomDatabase() {

    abstract fun cryptoCurrenciesDao(): CryptoCurrenciesDao
}