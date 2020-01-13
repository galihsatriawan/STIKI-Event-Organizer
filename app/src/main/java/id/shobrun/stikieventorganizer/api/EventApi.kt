package id.shobrun.stikieventorganizer.api

import androidx.lifecycle.LiveData
import id.shobrun.stikieventorganizer.models.entity.Event
import id.shobrun.stikieventorganizer.models.network.EventsResponse
import retrofit2.http.*

interface EventApi {
    @POST("event/myevents")
    @FormUrlEncoded
    fun getMyEvents(@Field("id") id : Int) : LiveData<ApiResponse<EventsResponse>>

    @POST("event/detail")
    @FormUrlEncoded
    fun getDetailEvent(@Field("id") eventId : String) : LiveData<ApiResponse<EventsResponse>>

    @POST("event/add")
    fun insertEvent(@Body event: HashMap<String, Event>) : LiveData<ApiResponse<EventsResponse>>

    @POST("event/edit")
    fun updateEvent(@Body event: HashMap<String, Event>) : LiveData<ApiResponse<EventsResponse>>
}