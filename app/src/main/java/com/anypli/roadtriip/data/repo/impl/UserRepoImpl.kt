package com.anypli.roadtriip.data.repo.impl

import com.anypli.roadtriip.base.BaseRepository
import com.anypli.roadtriip.data.model.LogResponse
import com.anypli.roadtriip.data.model.LogUserBody
import com.anypli.roadtriip.data.model.PasswordUpdateBody
import com.anypli.roadtriip.data.model.UpdateResponse
import com.anypli.roadtriip.data.model.User
import com.anypli.roadtriip.data.model.UserBody
import com.anypli.roadtriip.data.repo.abs.UserRepo
import com.anypli.roadtriip.ui.home.HomeScreen
import com.google.gson.Gson
class UserRepoImpl() : BaseRepository(), UserRepo {

    private val gson = Gson()
    override suspend fun createUser(
        firstName: String ,
        lastName: String ,
        phoneNumber: String ,
        email: String ,
        password: String ,
        profilePicture: String
    ):User {
        val userBody  = UserBody(firstName, lastName, phoneNumber, email, password, profilePicture)
        return api.createUser(userBody)
    }
    override suspend fun login(
        email: String ,
        password: String ,
    ): LogResponse {
        val LogUserBody = LogUserBody(email, password)
        return api.login(LogUserBody)
    }
    override suspend fun saveUser(user: User) {
        preferences.saveUser(user)
    }
    override suspend fun getUser(): User? {
        return preferences.getUser()
    }
    override suspend fun deleteUser(
        userId: String
    ) {
        api.deleteUser(userId)
        preferences.clearPreferences()
    }
    override suspend fun updateUser(userId: String , userBody: UserBody): UpdateResponse {
        return api.updateUser(userId, userBody)
    }
    override suspend fun forgetPassword () {
    }
    override suspend fun updatePassword (
        userId: String ,
        updatePasswordBody: PasswordUpdateBody
    ): UpdateResponse {
        return api.updatePassword(userId ,updatePasswordBody)

    }
}