package com.example.dummy_app.repository

import androidx.room.Database
import com.example.dummy_app.api.RetrofitInstance
import com.example.dummy_app.db.UserDatabase
import com.example.dummy_app.model.User

class UserRepository(val database: UserDatabase) {

    suspend fun getUsers() =
        RetrofitInstance.api.getUsers()


    fun getUserInfo() =
        database.getUserDao().getAllUsersInfo()

    suspend fun saveUserInfo(user:User) =
        database.getUserDao().insert(user)


}
