package com.jr.mvvm_offline

import android.app.Activity
import android.app.Application
import com.jr.mvvm_offline.di.components.DaggerAppComponent
import com.jr.mvvm_offline.di.modules.AppModule
import com.jr.mvvm_offline.di.modules.NetworkModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class CryptoCurrencyApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .networkModule(NetworkModule(BuildConfig.URL))
                .build().inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector
}