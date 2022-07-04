package com.example.dummy_app.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel(activity: Activity) : ViewModel() {
        val connected = MutableLiveData<Boolean>()

        init {
            val manager =
                activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkRequest = NetworkRequest.Builder()
                .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                .build()
            manager.registerNetworkCallback(networkRequest, object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    connected.postValue(true)
                }

                override fun onLost(network: Network) {
                    connected.postValue(false)
                }

                override fun onUnavailable() {
                    connected.postValue(false)
                }
            })
            connected.postValue(manager.isDefaultNetworkActive.not())
        }
    }