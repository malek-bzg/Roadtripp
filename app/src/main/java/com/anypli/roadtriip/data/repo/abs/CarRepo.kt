package com.anypli.roadtriip.data.repo.abs

interface CarRepo {


    suspend fun createCar(
        name: String ,
        color: String ,
        userId:String ,
        carPicture: String
    )


    suspend fun updateCar()

    suspend fun deletCar()

}