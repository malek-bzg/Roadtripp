package com.anypli.roadtriip.data.retrofit

import com.anypli.roadtriip.data.model.CarBody
import com.anypli.roadtriip.data.model.Event
import com.anypli.roadtriip.data.model.EventBody
import com.anypli.roadtriip.data.model.LogResponse
import com.anypli.roadtriip.data.model.LogUserBody
import com.anypli.roadtriip.data.model.PasswordUpdateBody
import com.anypli.roadtriip.data.model.UpdateResponse
import com.anypli.roadtriip.data.model.User
import com.anypli.roadtriip.data.model.UserBody
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import java.util.HashMap

interface ApiClient {
    @GET("yourendpoint/{param}")
    suspend fun getData(@Path("param") param : String?) :Call<User>
    @POST("users/login")
    suspend fun login(@Body post: LogUserBody) :LogResponse
    @POST("users")
    suspend fun createUser(@Body post: UserBody) :User
    @DELETE("users/{userId}")
    suspend fun deleteUser(@Path("userId") userId :String)
    @PUT("users/{userId}")
    suspend fun updateUser(@Path("userId") userId :String, @Body userBody: UserBody): UpdateResponse
    @PUT("users/{userId}/update-password")
    suspend fun updatePassword(@Path("userId") userId: String , @Body passwordUpdate: PasswordUpdateBody): UpdateResponse
    @POST("events/create")
    suspend fun createEvent(@Body post: EventBody) :Event
    @DELETE("events/{event_id}")
    fun deleteEvent(@Path("event_id") event_id:String) :Call<JsonObject>
    @FormUrlEncoded
    @PUT("events/{event_id}")
    fun updateEvent(@FieldMap params: HashMap<String?, String?>) :Call<JsonObject>
    @POST("cars/create")
    suspend fun createCar(@Body post: CarBody)
    @DELETE("cars/{car_id}")
    fun deleteCar(@Path("car_id") car_id:String): Call<JsonObject>
    @FormUrlEncoded
    @PUT("cars/{car_id}")
    fun updateCar(@FieldMap params: HashMap<String?, String?>): Call<JsonObject>
    @GET("/users")
    fun index(@Path("user") user_id: String) :Call<JsonObject>
    @GET("events")
   suspend fun showEvents() : List<Event>
//    @GET("/cars")
//    fun showCars(@Path("car") car_id: String) :Call<JsonObject>

}