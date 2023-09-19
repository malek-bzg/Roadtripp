package com.anypli.roadtriip.data.model


import com.google.gson.annotations.SerializedName

data class LogUserBody (

    @SerializedName("email") val email: String?="" ,
    @SerializedName("password") val password: String?="" ,

) {
    override fun toString(): String {
        return "LogUserBody( email=$email, password=$password)"
    }
}