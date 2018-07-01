package com.jr.mvvm_offline.utils

import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Inject

class Utils @Inject constructor(private val context: Context) {
    fun isConnected(): Boolean {
        val connectivity = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val info = connectivity.activeNetworkInfo

        if(info != null) {
            return true
        }

        return false
    }
}