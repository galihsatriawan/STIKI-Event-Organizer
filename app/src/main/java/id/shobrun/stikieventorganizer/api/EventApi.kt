package id.shobrun.stikieventorganizer.api

import androidx.lifecycle.LiveData
import id.shobrun.stikieventorganizer.models.network.EventsResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Query

interface EventApi {
    @POST("event/myevents")
    @FormUrlEncoded
    fun getMyEvents(@Field("id") id : Int) : LiveData<ApiResponse<EventsResponse>>

    @POST("event/detail")
    @FormUrlEncoded
    fun getDetailEvent(@Field("id") eventId : Int) : LiveData<ApiResponse<EventsResponse>>
}