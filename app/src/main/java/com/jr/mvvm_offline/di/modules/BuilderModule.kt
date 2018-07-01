package com.jr.mvvm_offline.di.modules

import com.jr.mvvm_offline.ui.list.CryptoCurrenciesActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeCryptoCurrenciesActivity(): CryptoCurrenciesActivity
}