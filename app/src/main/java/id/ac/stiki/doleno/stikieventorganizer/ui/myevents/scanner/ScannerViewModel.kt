package id.ac.stiki.doleno.stikieventorganizer.ui.myevents.scanner

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import id.ac.stiki.doleno.stikieventorganizer.models.Resource
import id.ac.stiki.doleno.stikieventorganizer.models.Status
import id.ac.stiki.doleno.stikieventorganizer.models.entity.Invitation
import id.ac.stiki.doleno.stikieventorganizer.models.entity.InvitationStatus
import id.ac.stiki.doleno.stikieventorganizer.models.network.InvitationsResponse
import id.ac.stiki.doleno.stikieventorganizer.repository.InvitationRepository
import id.ac.stiki.doleno.stikieventorganizer.utils.AbsentLiveData
import id.ac.stiki.doleno.stikieventorganizer.utils.Helper.getCurrentDatetime
import id.ac.stiki.doleno.stikieventorganizer.utils.SharedPref
import javax.inject.Inject

class ScannerViewModel @Inject constructor(
    repository: InvitationRepository,
    val sharedPref: SharedPref
) : ViewModel() {
    val loading: LiveData<Boolean>

    val updateInvitation: LiveData<Resource<Invitation, InvitationsResponse>>
    val invitationMutable = MutableLiveData<Invitation>()
    val invitationFromIntent = MutableLiveData<Invitation>()
    val loadingDetail: LiveData<Boolean>
    val invitationDetail: LiveData<Resource<Invitation, InvitationsResponse>>
    private val _snackbarText = MutableLiveData<String>()
    val snackbarText = _snackbarText

    init {
        updateInvitation = invitationMutable.switchMap {
            invitationMutable.value?.let {
                val data = repository.updateInvitation(it)
                data.value?.let { postInvitation(invitationFromIntent.value) }
                data
            } ?: AbsentLiveData.create()
        }
        loading = updateInvitation.switchMap {
            var isLoading = it.status == Status.LOADING
            if (!isLoading) {
                if (it.status == Status.ERROR)
                    _snackbarText.value = "Failed to update data"
                else{
                    _snackbarText.value = "Validate data successfully"
                }

            }
            MutableLiveData(isLoading)
        }
        invitationDetail = invitationFromIntent.switchMap {
            invitationFromIntent.value?.let {
                repository.getInvitationDetail(it.invitation_id ?: -1)
            } ?: AbsentLiveData.create()
        }
        loadingDetail = invitationDetail.switchMap {
            var isLoading = it.status == Status.LOADING
            if (!isLoading) {
                if (it.status == Status.ERROR) _snackbarText.value = "Please Check Your Connection"
            }
            MutableLiveData(isLoading)
        }
    }

    fun postInvitation(invitation: Invitation?) {
        invitationFromIntent.value = invitation

    }

    fun validateInvitation() {
        val invitation = invitationDetail.value?.data
        invitation?.status = InvitationStatus.ATTENDED.toString()
        invitation?.arrived_time = getCurrentDatetime()
        invitation?.let {
            invitationMutable.value = it
        }
    }
}