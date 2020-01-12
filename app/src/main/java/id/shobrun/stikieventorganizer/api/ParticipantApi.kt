package id.shobrun.stikieventorganizer.api

import androidx.lifecycle.LiveData
import id.shobrun.stikieventorganizer.models.entity.Participant
import id.shobrun.stikieventorganizer.models.network.ParticipantsResponse
import retrofit2.http.*

interface ParticipantApi {
    @POST("participant/myparticipants")
    @FormUrlEncoded
    fun getMyParticipants(@Field("id") id : Int) : LiveData<ApiResponse<ParticipantsResponse>>

    @POST("participant/detail")
    @FormUrlEncoded
    fun getDetailParticipant(@Field("id") id: String) : LiveData<ApiResponse<ParticipantsResponse>>

    @POST("participant/edit")
    fun updateParticipant(@Body participant : HashMap<String,Participant>) : LiveData<ApiResponse<ParticipantsResponse>>

    @POST("participant/add")
    fun addParticipant(@Body participant: HashMap<String,Participant>) : LiveData<ApiResponse<ParticipantsResponse>>
}