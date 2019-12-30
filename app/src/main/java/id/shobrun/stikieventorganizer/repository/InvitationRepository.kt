package id.shobrun.stikieventorganizer.repository

import androidx.lifecycle.LiveData
import id.shobrun.stikieventorganizer.AppExecutors
import id.shobrun.stikieventorganizer.api.ApiResponse
import id.shobrun.stikieventorganizer.api.InvitationApi
import id.shobrun.stikieventorganizer.models.entity.Invitation
import id.shobrun.stikieventorganizer.models.network.InvitationsResponse
import id.shobrun.stikieventorganizer.room.InvitationDao
import timber.log.Timber
import javax.inject.Inject

class InvitationRepository @Inject constructor(private val appExecutors: AppExecutors,private val apiService : InvitationApi, private val localDB : InvitationDao){
    companion object{
        val TAG = this.javaClass.name
    }
    fun getMyInvitation(email : String) = object : NetworkBoundRepository<List<Invitation>, InvitationsResponse>(appExecutors) {
        override fun saveFetchData(items: InvitationsResponse) {
           val invitations = items.result
            if(invitations!=null){
                localDB.inserts(invitations)
            }
        }

        override fun shouldFetch(data: List<Invitation>?): Boolean {
            return true
        }

        override fun loadFromDb(): LiveData<List<Invitation>> {
            return localDB.getMyInvitations()
        }

        override fun fetchService(): LiveData<ApiResponse<InvitationsResponse>> {
            return apiService.getMyInvitation(email)
        }

        override fun onFetchFailed(message: String?) {
            Timber.d("$TAG $message")
        }
    }.asLiveData()
}