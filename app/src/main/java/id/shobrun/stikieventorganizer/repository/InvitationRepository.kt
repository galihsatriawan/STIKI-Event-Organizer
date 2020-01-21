package id.shobrun.stikieventorganizer.repository

import androidx.lifecycle.LiveData
import id.shobrun.stikieventorganizer.AppExecutors
import id.shobrun.stikieventorganizer.api.ApiResponse
import id.shobrun.stikieventorganizer.api.InvitationApi
import id.shobrun.stikieventorganizer.models.entity.Invitation
import id.shobrun.stikieventorganizer.models.network.InvitationsResponse
import id.shobrun.stikieventorganizer.room.InvitationDao
import id.shobrun.stikieventorganizer.transporter.InvitationParticipantResponseTransporter
import id.shobrun.stikieventorganizer.transporter.InvitationResponseTransporter
import timber.log.Timber
import javax.inject.Inject

class InvitationRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val apiService: InvitationApi,
    private val localDB: InvitationDao
) {
    fun getInvitationDetail(id: Int) = object :
        NetworkBoundRepository<Invitation, InvitationsResponse, InvitationResponseTransporter>(
            appExecutors
        ) {
        override fun saveFetchData(items: InvitationsResponse) {
            if (items.result.isNullOrEmpty()) {
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

    fun getMyInvitation(email: String) = object :
        NetworkBoundRepository<List<Invitation>, InvitationsResponse, InvitationResponseTransporter>(
            appExecutors
        ) {
        override fun saveFetchData(items: InvitationsResponse) {
            if (!items.result.isNullOrEmpty()) {
                for (i in items.result) {
                    Timber.d("All Invitation- ${items.result}")
                }
                localDB.inserts(items.result)
            }
        }

        override fun shouldFetch(data: List<Invitation>?): Boolean {
            return true
        }

        override fun loadFromDb(): LiveData<List<Invitation>> {
            return localDB.getMyInvitations(email, true)
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

    fun getParticipantsEvent(userId: Int, eventId: String) = object :
        NetworkBoundRepository<List<Invitation>, InvitationsResponse, InvitationResponseTransporter>(
            appExecutors
        ) {
        override fun saveFetchData(items: InvitationsResponse) {
            if (!items.result.isNullOrEmpty()) {
                for (i in items.result) {
                    Timber.d("Participant event - ${items.result}")
                }
                localDB.inserts(items.result)
            }
        }

        override fun shouldFetch(data: List<Invitation>?): Boolean {
            return true
        }

        override fun loadFromDb(): LiveData<List<Invitation>> {
            return localDB.getInvitatationParticipants(userId, eventId)
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

    fun getAllParticipants(userId: Int, eventId: String) = object :
        NetworkBoundRepository<List<Invitation>, InvitationsResponse, InvitationParticipantResponseTransporter>(
            appExecutors
        ) {
        override fun saveFetchData(items: InvitationsResponse) {
            // no insert
        }

        override fun shouldFetch(data: List<Invitation>?): Boolean {
            return true
        }

        override fun loadFromDb(): LiveData<List<Invitation>> {
            return localDB.getInvitatationParticipants(userId, eventId)
        }

        override fun fetchService(): LiveData<ApiResponse<InvitationsResponse>> {
            return apiService.getInvitationAllParticipants(userId, eventId)
        }

        override fun transporter(): InvitationParticipantResponseTransporter {
            return InvitationParticipantResponseTransporter()
        }

        override fun onFetchFailed(message: String?) {
            Timber.d("$message")
        }
    }.asLiveData()

    fun updateInvitation(invitation: Invitation) = object :
        NetworkBoundRepository<Invitation, InvitationsResponse, InvitationResponseTransporter>(
            appExecutors
        ) {
        override fun saveFetchData(items: InvitationsResponse) {
            if (!items.result.isNullOrEmpty()) {
                localDB.inserts(items.result)
            }
        }

        override fun shouldFetch(data: Invitation?): Boolean {
            return true
        }

        override fun loadFromDb(): LiveData<Invitation> {
            return localDB.getDetailInvitation(invitation.invitation_id ?: -1)
        }

        override fun fetchService(): LiveData<ApiResponse<InvitationsResponse>> {
            val data = hashMapOf(
                "invitation" to invitation,
                "id" to invitation.invitation_id,
                "status" to "Attend"
            )
            return apiService.updateInvitation(data)
        }

        override fun transporter(): InvitationResponseTransporter {
            return InvitationResponseTransporter()
        }

        override fun onFetchFailed(message: String?) {
            Timber.d("$message")
        }
    }.asLiveData()

    fun updateEventParticipant(userId: Int, eventId: String, invitations: List<Invitation>) =
        object :
            NetworkBoundRepository<List<Invitation>, InvitationsResponse, InvitationParticipantResponseTransporter>(
                appExecutors
            ) {
            override fun saveFetchData(items: InvitationsResponse) {
//            if(items.result)
                /**
                 * Remove the data before
                 * then save it
                 */
                if (!items.result.isNullOrEmpty()) {
                    localDB.deleteByEventId(userId, eventId)
                    localDB.inserts(items.result)
                }
            }

            override fun shouldFetch(data: List<Invitation>?): Boolean {
                return true
            }

            override fun loadFromDb(): LiveData<List<Invitation>> {
                return localDB.getInvitatationParticipants(userId, eventId)
            }

            override fun fetchService(): LiveData<ApiResponse<InvitationsResponse>> {
                val data: HashMap<String, Any?> = hashMapOf(
                    "event_id" to eventId,
                    "user_id" to userId,
                    "invitations" to invitations
                )
                return apiService.updateParticipantEvent(data)
            }

            override fun transporter(): InvitationParticipantResponseTransporter {
                return InvitationParticipantResponseTransporter()
            }

            override fun onFetchFailed(message: String?) {
                Timber.d("$message")
            }

        }.asLiveData()
}