package com.anypli.roadtriip.data.preferences.abs

import com.anypli.roadtriip.data.model.User

interface Preferences {
    fun saveUser(user: User)
    fun getUser(): User?
    fun clearPreferences()

}