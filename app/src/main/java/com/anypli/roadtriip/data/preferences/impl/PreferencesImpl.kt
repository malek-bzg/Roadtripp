package com.anypli.roadtriip.data.preferences.impl

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.anypli.roadtriip.data.model.User
//import com.anypli.roadtriip.RoadTripApplication
import com.anypli.roadtriip.data.preferences.abs.Preferences
import com.google.gson.Gson


class PreferencesImpl( context: Context) : Preferences {

    private val mainKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

    private val sharedPreferences = EncryptedSharedPreferences.create(
        "myEncryptedPrefs",
        mainKeyAlias,
        context ,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM,
    )
    private val gson = Gson()
    override fun saveUser(user: User) {
        val userJsonString = gson.toJson(user)
        sharedPreferences.edit().putString("user_data", userJsonString).apply()
    }
    override fun clearPreferences() {
        sharedPreferences.edit().clear().apply()
    }
    override fun getUser(): User? {
        val userJsonString = sharedPreferences.getString("user_data", null)
        if (userJsonString != null) {
            return gson.fromJson(userJsonString, User::class.java)
        }
        return null
    }
}


