package id.shobrun.stikieventorganizer.api

import androidx.lifecycle.LiveData
import id.shobrun.stikieventorganizer.models.entity.Invitation
import id.shobrun.stikieventorganizer.models.network.InvitationsResponse
import retrofit2.http.*

interface InvitationApi {
    @POST("invitation/myinvitation")
    @FormUrlEncoded
    fun getMyInvitation(@Field("id") email : String) : LiveData<ApiResponse<InvitationsResponse>>

    @POST("invitation/status")
    @FormUrlEncoded
    fun getInvitationDetail(@Field("id") id : Int) : LiveData<ApiResponse<InvitationsResponse>>

    @POST("invitation/participants")
    @FormUrlEncoded
    fun getInvitationParticipants(@Field("id") id:String) : LiveData<ApiResponse<InvitationsResponse>>

    @POST("invitation/allparticipants")
    @FormUrlEncoded
    fun getInvitationAllParticipants(@Field("id") id:String) : LiveData<ApiResponse<InvitationsResponse>>

    @POST("invitation/update")
    fun updateInvitation(@Body data: HashMap<String,Any>) : LiveData<ApiResponse<InvitationsResponse>>
}