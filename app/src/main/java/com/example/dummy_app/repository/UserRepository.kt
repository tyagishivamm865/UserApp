package com.example.dummy_app.repository

import androidx.annotation.WorkerThread
import androidx.room.Database
import com.example.dummy_app.api.RetrofitInstance
import com.example.dummy_app.db.UserDatabase
import com.example.dummy_app.model.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(val database: UserDatabase) {

    suspend fun getUsers() =
        RetrofitInstance.api.getUsers()


    @WorkerThread
    suspend fun getUsersInfo(): List<User>{
        return database.getUserDao().getAllUsersInfo()
    }

    suspend fun saveUserInfo(user:List<User>) =
        database.getUserDao().insert(user)


}
