package com.anypli.roadtriip.data.repo.abs

import com.anypli.roadtriip.data.model.Event
import com.anypli.roadtriip.data.model.EventBody
import com.anypli.roadtriip.data.model.EventList
import com.anypli.roadtriip.data.model.User

interface EventRepo {

    suspend fun showEvents(): List<Event>

    suspend fun createEvent(
        nameDestination: String ,
        Dateofdeparture: String ,
        prix: String ,
        description: String ,
        maximumNumberOfplaces: String ,
        eventPicture: String
    ) : Event

    suspend fun updateEvent()

    suspend fun deletEvent()

}