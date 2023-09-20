package com.anypli.roadtriip.data.preferences.abs

import android.content.Context
import com.anypli.roadtriip.data.model.User

interface Preferences {
    fun saveUser(user: User)
    fun getUser(): User?
    fun clearPreferences()
    fun clearActivityStack(context: Context , destinationActivity: Class<*>)

}