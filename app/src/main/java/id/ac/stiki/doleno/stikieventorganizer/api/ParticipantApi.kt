package id.ac.stiki.doleno.stikieventorganizer.api

import androidx.lifecycle.LiveData
import id.ac.stiki.doleno.stikieventorganizer.models.entity.Participant
import id.ac.stiki.doleno.stikieventorganizer.models.network.ParticipantsResponse
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ParticipantApi {
    @POST("participant/myparticipants")
    @FormUrlEncoded
    fun getMyParticipants(@Field("id") id: Int): LiveData<ApiResponse<ParticipantsResponse>>

    @POST("participant/detail")
    @FormUrlEncoded
    fun getDetailParticipant(@Field("id") id: String): LiveData<ApiResponse<ParticipantsResponse>>

    @POST("participant/edit")
    fun updateParticipant(@Body participant: HashMap<String, Participant>): LiveData<ApiResponse<ParticipantsResponse>>

    @POST("participant/add")
    fun addParticipant(@Body participant: HashMap<String, Participant>): LiveData<ApiResponse<ParticipantsResponse>>
}