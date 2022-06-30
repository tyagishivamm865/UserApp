package com.example.dummy_app.model

import android.opengl.Visibility
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserResponse(
    @Expose
    @SerializedName("limit")
    val limit: Int,
    @Expose
    @SerializedName("skip")
    val skip: Int,
    @Expose
    @SerializedName("total")
    val total: Int,
    @Expose
    @SerializedName("users")
    val users: List<User>
)