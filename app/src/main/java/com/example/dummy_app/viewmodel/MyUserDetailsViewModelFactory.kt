package com.example.dummy_app.viewmodel

import android.app.Activity
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dummy_app.repository.UserRepository

class MyUserDetailsViewModelFactory(val activity: Activity) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MyViewModel(activity) as T
    }

}