package com.example.dummy_app.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "usertable")
data class User(
    @Expose
    @SerializedName("visibility")
    var visibility: Boolean = false,
    @Expose
    @SerializedName("address")
    val address: Address?,
    @Expose
    @SerializedName("age")
    val age: Int,
    @Expose
    @SerializedName("birthDate")
    val birthDate: String,
    @Expose
    @SerializedName("domain")
    val domain: String,
    @Expose
    @SerializedName("email")
    val email: String,
    @Expose
    @SerializedName("firstName")
    val firstName: String,
    @Expose
    @SerializedName("gender")
    val gender: String,
    @Expose
    @SerializedName("id")
    @PrimaryKey
    val id: Int,
    @Expose
    @SerializedName("image")
    val image: String,
    @Expose
    @SerializedName("lastName")
    val lastName: String,
    @Expose
    @SerializedName("maidenName")
    val maidenName: String,
    @Expose
    @SerializedName("password")
    val password: String,
    @Expose
    @SerializedName("phone")
    val phone: String,
    @Expose
    @SerializedName("ssn")
    val ssn: String,
    @Expose
    @SerializedName("university")
    val university: String,
    @Expose
    @SerializedName("username")
    val username: String
)