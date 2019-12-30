package id.shobrun.stikieventorganizer.api

import androidx.lifecycle.LiveData
import id.shobrun.stikieventorganizer.models.network.EventsResponse
import retrofit2.http.POST
import retrofit2.http.Query

interface EventApi {
    @POST("event/myevents")
    fun getMyEvents(@Query("id") id : Int) : LiveData<ApiResponse<EventsResponse>>

    @POST("event/detail")
    fun getDetailEvent(@Query("id") eventId : Int) : LiveData<ApiResponse<EventsResponse>>
}