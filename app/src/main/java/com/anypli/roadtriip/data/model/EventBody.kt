package com.anypli.roadtriip.data.model

import com.google.gson.annotations.SerializedName

data class EventBody (

    @SerializedName("nameDestination") val nameDestination  : String ,
    @SerializedName("Dateofdeparture") val Dateofdeparture: String ,
    @SerializedName("prix") val prix: String ,
    @SerializedName("description") val description: String ,
    @SerializedName("maximumNumberOfplaces") val maximumNumberOfplaces: String ,
    @SerializedName("eventPicture") val eventPicture: String?="" ,
){
    override fun toString(): String {
        return "EventBody( nameDestination=$nameDestination, Dateofdeparture=$Dateofdeparture, cin=$prix, description=$description, maximumNumberOfplaces=$maximumNumberOfplaces, eventPicture=$eventPicture)"
    }
}