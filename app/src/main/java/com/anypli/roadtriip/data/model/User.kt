package com.anypli.roadtriip.data.model

import BASE_URL
import SERVER_URL
import com.google.gson.annotations.SerializedName

data class User (

    @SerializedName("id") val id: String?="",
    @SerializedName("Fname") val Fname  : String?="" ,
    @SerializedName("Lname") val Lname: String?="" ,
    @SerializedName("phone_number") val phone_number: String?="" ,
    @SerializedName("email") val email: String?="" ,
    @SerializedName("password") val password: String?="" ,
    @SerializedName("profilePicture") val profilePicture: String?="" ,
    @SerializedName("role") val role: String?=""
) {
    override fun toString(): String {
        return "User(id=$id Fname=$Fname, Lname=$Lname, phone_number=$phone_number, email=$email, password=$password ,role=$role ,profilePicture=$profilePicture)"
    }

  fun getUserPicture(): String{
      if (profilePicture!=null){
          return SERVER_URL+profilePicture;
      }
     return "";
    }
}