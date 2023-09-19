package com.anypli.roadtriip.data.model

import com.google.gson.annotations.SerializedName
import com.google.type.DateTime


data class Event (

    @SerializedName("id") val id: String,
    @SerializedName("nameDestination") val nameDestination  : String,
    @SerializedName("Dateofdeparture") val Dateofdeparture: String ,
    @SerializedName("prix") val prix: String,
    @SerializedName("description") val description: String,
    @SerializedName("maximumNumberOfplaces") val maximumNumberOfplaces: String,
    @SerializedName("eventPicture") val eventPicture: String?="",
){
    override fun toString(): String {
        return "User(id=$id, nameDestination=$nameDestination, Dateofdeparture=$Dateofdeparture, cin=$prix, description=$description, maximumNumberOfplaces=$maximumNumberOfplaces, eventPicture=$eventPicture)"
    }
}