package com.anypli.roadtriip.di

import android.content.SharedPreferences
import com.anypli.roadtriip.data.repo.abs.CarRepo
import com.anypli.roadtriip.data.repo.abs.EventRepo
import com.anypli.roadtriip.data.repo.abs.UserRepo
import com.anypli.roadtriip.data.repo.impl.CarRepoImpl
import com.anypli.roadtriip.data.repo.impl.EventRepoImpl
import com.anypli.roadtriip.data.repo.impl.UserRepoImpl

object RepositoriesUtility {

    private lateinit var sharedPreferences: SharedPreferences

    private val userRepository: UserRepo =UserRepoImpl()

    private val eventRepository: EventRepo = EventRepoImpl()

    private val carRepository: CarRepo = CarRepoImpl()

    fun getUserRepository(): UserRepo {
        return userRepository
    }
    fun getEventRepository(): EventRepo {
        return eventRepository
    }
    fun getCarRepository(): CarRepo {
        return carRepository
    }

}