package com.example.dummy_app.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dummy_app.R
import com.example.dummy_app.adapters.UserAdapter
import com.example.dummy_app.db.UserDatabase
import com.example.dummy_app.repository.UserRepository
import com.example.dummy_app.util.Resource
import com.example.dummy_app.viewmodel.MyViewModel
import com.example.dummy_app.viewmodel.UserDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_user_details.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class UserDetails : AppCompatActivity() {
    val viewModel: UserDetailsViewModel by viewModels()
    lateinit var userAdapter: UserAdapter
    val myviewModel: MyViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)


        Log.d("loading1", "Loading heavily")


        myviewModel.connected.observe(this, {
            Log.d("user", "${it}")
            if (it.not()) {
                loadUserDatabaseIfAvailable()
            } else {
                Toast.makeText(this, "Network Connection", Toast.LENGTH_SHORT).show()
                Log.d("loaduser", "Loading")
                viewModel.getUsers()
                viewModel.userData.observe(this, Observer { response ->
                    when (response) {
                        is Resource.Success -> {
//                    hideProgressBar()
                            response.data?.let { userResponse ->

                                viewModel.saveUser(userResponse.users)
                                userAdapter = UserAdapter(this, userResponse.users)
                                recyclerview.adapter = userAdapter
                                recyclerview.layoutManager = LinearLayoutManager(this)
                            }
                        }
                        is Resource.Error -> {
//                    hideProgressBar()
                            response.message?.let {
                                Log.e("usermessage", "An error occured:$it")
                            }
                        }
                        is Resource.Loading -> {
//                    showProgressBar()
                            Log.e("loading", "Loading heavily")

                        }
                    }

                })

            }
        })


    }

    protected fun isOnline(): Boolean {
        val manager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkRequest = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .build()
        return manager.isDefaultNetworkActive.not()
    }
//    fun checkConnection() {
//        if (isOnline()) {
//            Toast.makeText(this, "You are connected to Internet", Toast.LENGTH_SHORT)
//                .show()
//        } else {
//            Toast.makeText(
//                this,"You are not connected to Internet",
//                Toast.LENGTH_SHORT
//            ).show()
//        }
//    }

    private fun loadUserDatabaseIfAvailable() {
        lifecycleScope.launch {
            viewModel.getUsersInfo().let {
                if (it.isNotEmpty()) {

                    userAdapter = UserAdapter(this@UserDetails, it)
                    recyclerview.adapter = userAdapter
                    recyclerview.layoutManager = LinearLayoutManager(this@UserDetails)
                }
            }
        }
    }

}
