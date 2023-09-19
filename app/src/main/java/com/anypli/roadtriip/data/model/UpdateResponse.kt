package com.anypli.roadtriip.data.model

import com.google.gson.annotations.SerializedName



class UpdateResponse(


    @SerializedName("updateUser") val updateUser: User

) {
    override fun toString(): String {
        return "updateBody( updateUser=$updateUser)"
    }
}