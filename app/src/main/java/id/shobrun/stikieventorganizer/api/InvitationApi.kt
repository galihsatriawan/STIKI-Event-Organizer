package id.shobrun.stikieventorganizer.api

import androidx.lifecycle.LiveData
import id.shobrun.stikieventorganizer.models.entity.Invitation
import id.shobrun.stikieventorganizer.models.network.InvitationsResponse
import retrofit2.http.POST
import retrofit2.http.Query

interface InvitationApi {
    @POST("invitation/myinvitation")
    fun getMyInvitation(@Query("id") email : String) : LiveData<ApiResponse<InvitationsResponse>>

    @POST("invitation/status")
    fun getInvitationDetail(@Query("id") id : Int) : LiveData<ApiResponse<InvitationsResponse>>
}