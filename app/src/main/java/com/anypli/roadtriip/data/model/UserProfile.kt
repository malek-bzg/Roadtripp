package com.anypli.roadtriip.data.model

import com.google.gson.annotations.SerializedName

data class UserProfile(

    @SerializedName("Fname") val Fname  : String?="" ,
    @SerializedName("Lname") val Lname: String?="" ,
    @SerializedName("phone_number") val phone_number: String?="" ,
    @SerializedName("email") val email: String?="" ,
    @SerializedName("password") val password: String?="" ,
    @SerializedName("profilePicture") val profilePicture: String?="" ,
) {
    override fun toString(): String {
        return "UserProfile( firstName=$Fname, lastName=$Lname, phoneNumber=$phone_number, email=$email, password=$password,profilePicture=$profilePicture)"
    }
}

