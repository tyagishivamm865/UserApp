package com.example.dummy_app.repository

import androidx.annotation.WorkerThread
import androidx.room.Database
import com.example.dummy_app.api.RetrofitInstance
import com.example.dummy_app.db.UserDao
import com.example.dummy_app.db.UserDatabase
import com.example.dummy_app.model.User

class UserRepository(val database: UserDatabase) {

    suspend fun getUsers() =
        RetrofitInstance.api.getUsers()

//    @WorkerThread
//    suspend fun getUsersInfo(): List<User>{
//        return database.getUserDao().getAllUsersInfo()
//    }

    suspend fun saveUserInfo(user:List<User>) =
        database.getUserDao().insert(user)


}
