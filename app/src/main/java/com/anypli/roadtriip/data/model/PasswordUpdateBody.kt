package com.anypli.roadtriip.data.model




import com.google.gson.annotations.SerializedName

 data class PasswordUpdateBody (
    @SerializedName("currentPassword") var currentPassword: String? = "",
    @SerializedName("newPassword") var newPassword: String? = "",

) {
    override fun toString(): String {
        return "PasswordUpdateBody(currentPassword=$currentPassword, newPassword=$newPassword)"
    }
 }




