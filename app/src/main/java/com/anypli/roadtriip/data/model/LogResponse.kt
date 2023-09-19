package com.anypli.roadtriip.data.model

import com.google.gson.annotations.SerializedName


data class LogResponse (

    @SerializedName("message") val message: String?="" ,
    @SerializedName("user") val user: User

    ) {
    override fun toString(): String {
        return "LogUserBody( message=$message, user=$user)"
    }
}