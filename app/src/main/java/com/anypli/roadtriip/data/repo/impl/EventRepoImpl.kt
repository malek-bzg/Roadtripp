package com.anypli.roadtriip.data.repo.impl

import com.anypli.roadtriip.base.BaseRepository
import com.anypli.roadtriip.data.model.Event
import com.anypli.roadtriip.data.model.EventBody
import com.anypli.roadtriip.data.model.EventList
import com.anypli.roadtriip.data.model.User
import com.anypli.roadtriip.data.repo.abs.EventRepo


class EventRepoImpl: BaseRepository() , EventRepo {


    override suspend fun createEvent(
        nameDestination: String ,
        Dateofdeparture: String ,
        prix: String ,
        description: String ,
        maximumNumberOfplaces: String ,
        eventPicture: String
        ) : Event {
        val eventBody  = EventBody(nameDestination, Dateofdeparture, prix, description, maximumNumberOfplaces,eventPicture)
        return api.createEvent(eventBody)
    }



    override suspend fun showEvents(): List<Event> {

        return api.showEvents()

    }


    override suspend fun updateEvent(){

    }

    override suspend fun deletEvent() {

    }

}









