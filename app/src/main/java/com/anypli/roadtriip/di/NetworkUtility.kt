package com.anypli.roadtriip.di
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.Request
import okhttp3.Response

object NetworkUtility {
    private var client: OkHttpClient? = null

    // Méthode pour obtenir l'instance du OkHttpClient
    fun getClient(): OkHttpClient {
        if (client == null) {
            synchronized(NetworkUtility::class.java) {
                if (client == null) {
                    // Créer un interceptor pour les logs
                    val loggingInterceptor = HttpLoggingInterceptor()
                    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

                    // Créer un OkHttpClient et lui ajouter l'interceptor
                    client = OkHttpClient.Builder()
                        .addInterceptor(loggingInterceptor)
                        .build()
                }

            }
        }
        return client!!
    }



}




