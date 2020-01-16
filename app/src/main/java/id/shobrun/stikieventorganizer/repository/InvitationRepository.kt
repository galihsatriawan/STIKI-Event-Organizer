package id.shobrun.stikieventorganizer.repository

import androidx.lifecycle.LiveData
import id.shobrun.stikieventorganizer.AppExecutors
import id.shobrun.stikieventorganizer.api.ApiResponse
import id.shobrun.stikieventorganizer.api.InvitationApi
import id.shobrun.stikieventorganizer.models.entity.Event
import id.shobrun.stikieventorganizer.models.entity.Invitation

import id.shobrun.stikieventorganizer.models.network.InvitationsResponse
import id.shobrun.stikieventorganizer.room.InvitationDao
import id.shobrun.stikieventorganizer.transporter.InvitationResponseTransporter
import timber.log.Timber
import javax.inject.Inject

class InvitationRepository @Inject constructor(private val appExecutors: AppExecutors,private val apiService : InvitationApi, private val localDB : InvitationDao){
    fun getInvitationDetail(id : Int) = object : NetworkBoundRepository<Invitation,InvitationsResponse,InvitationResponseTransporter>(appExecutors){
        override fun saveFetchData(items: InvitationsResponse) {
            if(items.result.isNullOrEmpty()){
                localDB.insert(items.result[0])
            }
        }

        override fun shouldFetch(data: Invitation?): Boolean {
            return true
        }

        override fun loadFromDb(): LiveData<Invitation> {
            return localDB.getDetailInvitation(id)
        }

        override fun fetchService(): LiveData<ApiResponse<InvitationsResponse>> {
            return apiService.getInvitationDetail(id)
        }

        override fun onFetchFailed(message: String?) {
            Timber.d("$message")
        }

        override fun transporter(): InvitationResponseTransporter {
            return InvitationResponseTransporter()
        }
    }.asLiveData()
    fun getMyInvitation(email : String) = object : NetworkBoundRepository<List<Invitation>, InvitationsResponse,InvitationResponseTransporter>(appExecutors) {
        override fun saveFetchData(items: InvitationsResponse) {
            if(!items.result.isNullOrEmpty()){
                localDB.inserts(items.result)
            }
        }

        override fun shouldFetch(data: List<Invitation>?): Boolean {
            return true
        }

        override fun loadFromDb(): LiveData<List<Invitation>> {
            return localDB.getMyInvitations(email)
        }

        override fun fetchService(): LiveData<ApiResponse<InvitationsResponse>> {
            return apiService.getMyInvitation(email)
        }

        override fun onFetchFailed(message: String?) {
            Timber.d("$message")
        }

        override fun transporter(): InvitationResponseTransporter {
            return InvitationResponseTransporter()
        }
    }.asLiveData()

    fun getParticipantsEvent(eventId: String) = object : NetworkBoundRepository<List<Invitation>,InvitationsResponse,InvitationResponseTransporter>(appExecutors){
        override fun saveFetchData(items: InvitationsResponse) {
            if(!items.result.isNullOrEmpty()){
                Timber.d("${items.result?.get(0).toString()}")
                localDB.inserts(items.result)
            }
        }

        override fun shouldFetch(data: List<Invitation>?): Boolean {
            return true
        }

        override fun loadFromDb(): LiveData<List<Invitation>> {
            return localDB.getInvitatationParticipants(eventId)
        }

        override fun fetchService(): LiveData<ApiResponse<InvitationsResponse>> {
            return apiService.getInvitationParticipants(eventId)
        }

        override fun transporter(): InvitationResponseTransporter {
            return InvitationResponseTransporter()
        }

        override fun onFetchFailed(message: String?) {
            Timber.d("$message")
        }
    }.asLiveData()
    fun getAllParticipants(eventId: String) = object : NetworkBoundRepository<List<Invitation>,InvitationsResponse,InvitationResponseTransporter>(appExecutors){
        override fun saveFetchData(items: InvitationsResponse) {
            if(!items.result.isNullOrEmpty()){
                Timber.d("${items.result?.get(0).toString()}")
                localDB.inserts(items.result)
            }
        }

        override fun shouldFetch(data: List<Invitation>?): Boolean {
            return true
        }

        override fun loadFromDb(): LiveData<List<Invitation>> {
            return localDB.getInvitatationParticipants(eventId)
        }

        override fun fetchService(): LiveData<ApiResponse<InvitationsResponse>> {
            return apiService.getInvitationAllParticipants(eventId)
        }

        override fun transporter(): InvitationResponseTransporter {
            return InvitationResponseTransporter()
        }

        override fun onFetchFailed(message: String?) {
            Timber.d("$message")
        }
    }.asLiveData()
}