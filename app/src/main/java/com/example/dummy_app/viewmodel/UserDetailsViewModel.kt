package com.example.dummy_app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dummy_app.db.UserDatabase
import com.example.dummy_app.model.User
import com.example.dummy_app.model.UserResponse
import com.example.dummy_app.repository.UserRepository
import com.example.dummy_app.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    val userRepository: UserRepository
) : ViewModel() {
    val userData: MutableLiveData<Resource<UserResponse>> = MutableLiveData()


//    init {
//        getUsers()
//    }

    fun getUsers() = viewModelScope.launch {
        userData.postValue(Resource.Loading())
        val response = userRepository.getUsers()

        userData.postValue(handleUserResponse(response))
    }


    suspend fun getUsersInfo(): List<User>{
        return withContext(Dispatchers.IO){
            return@withContext userRepository.getUsersInfo()
        }
    }




    fun saveUser(user: List<User>) = viewModelScope.launch {
        userRepository.saveUserInfo(user)
    }


    }

    private  fun handleUserResponse(response: Response<UserResponse>): Resource<UserResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())

    }




//if(it!=null)
//{
//    insertData(User(false,it[0].address,it[0].age,it[0].birthDate,it[0].domain,it[0].email,it[0].firstName,it[0].gender,it[0].id,
//        it[0].image,it[0].lastName,it[0].maidenName,it[0].password,it[0].phone,it[0].ssn,it[0].university,it[0].username))
//}