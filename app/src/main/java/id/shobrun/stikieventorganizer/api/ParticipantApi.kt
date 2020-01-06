package id.shobrun.stikieventorganizer.api

import androidx.lifecycle.LiveData
import id.shobrun.stikieventorganizer.models.network.ParticipantsResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Query

interface ParticipantApi {
    @POST("participant/myparticipants")
    @FormUrlEncoded
    fun getMyParticipants(@Field("id") id : Int) : LiveData<ApiResponse<ParticipantsResponse>>
}