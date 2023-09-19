package com.anypli.roadtriip.data.retrofit

import android.content.Context
import android.content.SharedPreferences
import com.anypli.roadtriip.data.model.User

object UserSession {

    const val PREFS_NAME = "user_session_prefs"

    private lateinit var sharedPreferences: SharedPreferences

    // Mettez à jour cette fonction pour sauvegarder les données de session
    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        loadSessionData()
    }

    var id = ""
    var firstName = ""
    var lastName = ""
    var profilePicture = "default-profile.png"
    var email = ""
    var password = ""
    var phone_number = ""


    fun fullName(): String {
        return "$firstName $lastName"
    }

    fun reset() {
        id = ""
        firstName = ""
        lastName = ""
        profilePicture = "default-profile.png"
        email = ""
        password = ""
        phone_number = ""
        saveSessionData()
    }
    fun loginUser(result: User) {
        id = result.id.toString()
        firstName = result.Fname.toString()
        lastName = result.Lname.toString()
        profilePicture = result.profilePicture ?: "default-profile.png"
        email = result.email.toString()
        phone_number = result.phone_number.toString()
        password = result.password.toString()

        saveSessionData()
    }

    private fun saveSessionData() {
        val editor = sharedPreferences.edit()
        editor.putString("id", id)
        editor.putString("firstName", firstName)
        editor.putString("lastName", lastName)
        editor.putString("profilePicture", profilePicture)
        editor.putString("email", email)
        editor.putString("password", password)
        editor.putString("phone_number", phone_number)

        editor.apply()
    }

    private fun loadSessionData() {
        id = sharedPreferences.getString("id", "") ?: ""
        firstName = sharedPreferences.getString("firstName", "") ?: ""
        lastName = sharedPreferences.getString("lastName", "") ?: ""
        profilePicture = sharedPreferences.getString("profilePicture", "default-profile.png") ?: "default-profile.png"
        email = sharedPreferences.getString("email", "") ?: ""
        password = sharedPreferences.getString("password", "") ?: ""
        phone_number = sharedPreferences.getString("phone_number", "") ?: ""

        //isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)
    }
}
