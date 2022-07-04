package com.example.dummy_app.db

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseProvider {
    @Provides
    @Singleton
    fun createDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context.applicationContext, UserDatabase::class.java,
            "user_db.db"
        ).build()
}