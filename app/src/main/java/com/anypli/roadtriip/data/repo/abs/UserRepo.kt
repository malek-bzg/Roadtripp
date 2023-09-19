package com.anypli.roadtriip.data.repo.abs

import com.anypli.roadtriip.data.model.LogResponse
import com.anypli.roadtriip.data.model.PasswordUpdateBody
import com.anypli.roadtriip.data.model.UpdateResponse
import com.anypli.roadtriip.data.model.User
import com.anypli.roadtriip.data.model.UserBody


interface UserRepo {

    suspend fun createUser(
        firstName: String,
        lastName: String,
        phoneNumber: String,
        email: String,
        password: String,
        profilePicture: String
    ) : User

    suspend fun login(
        email: String,
        password: String,
    ) : LogResponse

    //suspend fun updateUser(userId: String)
    suspend fun updateUser(userId: String, userBody: UserBody): UpdateResponse
    suspend fun saveUser(userJson: User)
    suspend fun getUser(): User?
    suspend fun deleteUser(userId: String)
    suspend fun forgetPassword()

    suspend fun updatePassword(
        userId: String ,
        updatePasswordBody: PasswordUpdateBody
    ): UpdateResponse

}

