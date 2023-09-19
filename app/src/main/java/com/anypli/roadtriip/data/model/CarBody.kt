package com.anypli.roadtriip.data.model
import com.google.gson.annotations.SerializedName

data class CarBody (

    @SerializedName("name") val name: String?="" ,
    @SerializedName("color") val color: String?="" ,
    @SerializedName("userId") val userId: String?="",
    @SerializedName("carPicture") val carPicture: String?="" ,

) {
    override fun toString(): String {
        return "CarBody(name=$name, color=$color,userId=$userId ,carPicture=$carPicture)"
    }
}