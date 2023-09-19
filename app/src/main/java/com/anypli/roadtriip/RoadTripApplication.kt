package com.anypli.roadtriip
import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance

class RoadTripApplication: Application() {
    companion object {
          private lateinit var instance: RoadTripApplication
        fun getInstance(): RoadTripApplication{
            return instance
        }
    }
    override fun onCreate() {
        super.onCreate()
        instance = this



    }
}