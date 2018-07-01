package com.jr.mvvm_offline.di.components

import android.app.Application
import com.jr.mvvm_offline.CryptoCurrencyApplication
import com.jr.mvvm_offline.di.modules.AppModule
import com.jr.mvvm_offline.di.modules.BuilderModule
import com.jr.mvvm_offline.di.modules.NetworkModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [(AndroidInjectionModule::class), (BuilderModule::class), (AppModule::class),
            (NetworkModule::class)]
)
interface AppComponent {

    fun inject(application: CryptoCurrencyApplication)
}