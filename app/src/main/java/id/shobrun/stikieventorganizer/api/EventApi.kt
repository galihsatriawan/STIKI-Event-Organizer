package id.shobrun.stikieventorganizer.api

import androidx.lifecycle.LiveData
import id.shobrun.stikieventorganizer.models.network.EventsResponse
import retrofit2.http.POST
import retrofit2.http.Query

interface EventApi {
    @POST("invitation/myevent")
    fun getMyEvents(@Query("id") email : String) : LiveData<ApiResponse<EventsResponse>>

    @POST("invitation/detail")
    fun getDetailEvent(@Query("id") eventId : Int) : LiveData<ApiResponse<EventsResponse>>
}