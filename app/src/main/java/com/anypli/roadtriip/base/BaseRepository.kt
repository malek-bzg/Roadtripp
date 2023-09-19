package com.anypli.roadtriip.base

import ApiClientUtility
import com.anypli.roadtriip.data.retrofit.ApiClient
import com.anypli.roadtriip.di.PreferencesUtility


abstract class BaseRepository() {
    //La propriété api est initialisée en appelant la méthode getInstance() de l'objet ApiClientUtility
    protected val api: ApiClient = ApiClientUtility.getInstance()


    //La propriété preferences est initialisée en appelant la méthode getPreferences() de l'objet PreferencesUtility
    protected val preferences: com.anypli.roadtriip.data.preferences.abs.Preferences = PreferencesUtility.getPreferences()

}
























//abstract class BaseRepository {
//     val api: ApiClient
//     val preferences: Preferences
//
//
//    constructor(api: ApiClient , preferences: Preferences) {
//        this.api = api
//        this.preferences = preferences
//    }
//
//
//
////
////    init {
////        api = ApiClientUtility.getInstance()
////        preferences = PreferencesUtility.getPreferences()
////    }
//
//
//}




















//    abstract fun performApiCall()
//
//    abstract fun getPreference(key: String): String
//
//    abstract fun setPreference(key: String, value: String)