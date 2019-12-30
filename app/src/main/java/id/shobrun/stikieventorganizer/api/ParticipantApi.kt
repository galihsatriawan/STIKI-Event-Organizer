package id.shobrun.stikieventorganizer.api

import androidx.lifecycle.LiveData
import id.shobrun.stikieventorganizer.models.network.ParticipantsResponse
import retrofit2.http.POST
import retrofit2.http.Query

interface ParticipantApi {
    @POST("participant/myparticipants")
    fun getMyParticipants(@Query("id") id : Int) : LiveData<ApiResponse<ParticipantsResponse>>
}