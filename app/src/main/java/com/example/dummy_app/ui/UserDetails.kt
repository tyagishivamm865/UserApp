package com.example.dummy_app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.dummy_app.R
import com.example.dummy_app.adapters.UserAdapter
import com.example.dummy_app.db.UserDatabase
import com.example.dummy_app.model.User
import com.example.dummy_app.repository.UserRepository
import com.example.dummy_app.util.Resource
import com.example.dummy_app.viewmodel.UserDetailsViewModel
import com.example.dummy_app.viewmodel.UserDetailsViewModelFactory
import kotlinx.android.synthetic.main.activity_user_details.*

class UserDetails : AppCompatActivity() {
    lateinit var viewModel: UserDetailsViewModel
    lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)
        Log.d("loading1", "Loading heavily")

        val userrepository = UserRepository(UserDatabase(this))
        val viewModelProviderFactory = UserDetailsViewModelFactory(userrepository)
        viewModel = ViewModelProvider(this,viewModelProviderFactory).get(UserDetailsViewModel::class.java)

        viewModel.userData.observe(this, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    Log.d("loading2", "Loading heavily")

//                    hideProgressBar()
                    response.data?.let { userResponse ->
                        userResponse.users.let {
                            if(it!=null) {
                                viewModel.insertData(it[0]
                                )
                            }
                        }
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


}