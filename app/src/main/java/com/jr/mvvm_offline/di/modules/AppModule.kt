package com.jr.mvvm_offline.di.modules

import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Room
import android.arch.persistence.room.migration.Migration
import com.jr.mvvm_offline.data.source.local.dao.CryptoCurrenciesDao
import com.jr.mvvm_offline.data.source.local.dao.Database
import com.jr.mvvm_offline.ui.list.CryptoCurrenciesViewModelFactory
import com.jr.mvvm_offline.utils.Utils
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: Application) {

    companion object {
        val MIGRATION_2_3: Migration = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("ALTER TABLE cryptocurrency RENAME TO cryptocurrencies")
            }

        }
    }

    @Provides
    @Singleton
    fun provideApplication(): Application = app

    @Provides
    @Singleton
    fun provideCryptoCurrencyDatabase(app: Application): Database = Room
            .databaseBuilder(app, Database::class.java, "cryptocurrencies_db")
//            .addMigrations(MIGRATION_2_3)
            //this will destroy previous database on migration
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideCryptoCurrenciesDao(database: Database): CryptoCurrenciesDao = database.cryptoCurrenciesDao()

    @Provides
    fun provideCryptoCurrenciesViewModelFactory(
            factory: CryptoCurrenciesViewModelFactory
    ): ViewModelProvider.Factory = factory

    @Provides
    @Singleton
    fun provideUtils(): Utils = Utils(app)
}