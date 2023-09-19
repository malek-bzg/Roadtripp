package com.anypli.roadtriip.data.repo.impl

import com.anypli.roadtriip.base.BaseRepository
import com.anypli.roadtriip.data.model.CarBody
import com.anypli.roadtriip.data.repo.abs.CarRepo


class CarRepoImpl: BaseRepository() , CarRepo {

    override suspend fun createCar(
        name: String ,
        color: String ,
        userId:String ,
        carPicture: String
    ) {
        val carBody  = CarBody(name, color,userId ,carPicture)
        return api.createCar(carBody) }

    override suspend fun updateCar(){


    }

    override suspend fun deletCar(){

    }
}