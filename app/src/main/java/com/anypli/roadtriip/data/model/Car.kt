package com.anypli.roadtriip.data.model

import com.google.gson.annotations.SerializedName



data class Car (

    @SerializedName("id") val id: String?="",
    @SerializedName("name ") val name  : String?="",
    @SerializedName("color") val color: String?="",
    @SerializedName("userId") val userId: String?="",
    @SerializedName("carPicture") val carPicture: String?="",
){
    override fun toString(): String {
        return "Car(id=$id, name=$name, color=$color, userId=$userId , carPicture=$carPicture)"
    }
}