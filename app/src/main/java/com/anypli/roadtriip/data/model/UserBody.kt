package com.anypli.roadtriip.data.model

import com.google.gson.annotations.SerializedName

data class UserBody (

    @SerializedName("Fname") var Fname  : String ,
    @SerializedName("Lname") var Lname: String ,
    @SerializedName("phone_number") var phone_number: String? ,
    @SerializedName("email") var email: String ,
    @SerializedName("password") var password: String? ,
    @SerializedName("profilePicture") var profilePicture: String="" ,

    ) {
    override fun toString(): String {
        return "UserBody( firstName=$Fname, lastName=$Lname, phoneNumber=$phone_number, email=$email, password=$password,profilePicture=$profilePicture)"
    }
}