package id.shobrun.stikieventorganizer.ui.myevents.scanner

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import id.shobrun.stikieventorganizer.models.Resource
import id.shobrun.stikieventorganizer.models.Status
import id.shobrun.stikieventorganizer.models.entity.Invitation
import id.shobrun.stikieventorganizer.models.entity.InvitationStatus
import id.shobrun.stikieventorganizer.models.network.InvitationsResponse
import id.shobrun.stikieventorganizer.repository.InvitationRepository
import id.shobrun.stikieventorganizer.utils.AbsentLiveData
import javax.inject.Inject

class ScannerViewModel @Inject constructor(repository: InvitationRepository) : ViewModel(){
    val loading : LiveData<Boolean>

    val updateInvitation : LiveData<Resource<Invitation,InvitationsResponse>>
    val invitationMutable = MutableLiveData<Invitation>()
    val invitationFromIntent = MutableLiveData<Invitation>()
    val loadingDetail : LiveData<Boolean>
    private val invitationDetail : LiveData<Resource<Invitation,InvitationsResponse>>
    private val _snackbarText = MutableLiveData<String>()
    val snackbarText = _snackbarText
    init {
        updateInvitation = invitationMutable.switchMap {
            invitationMutable.value?.let {
                val data = repository.updateInvitation(it)
                data.value?.let { postInvitation(data.value?.data) }
                data
            }?:AbsentLiveData.create()
        }
        loading = updateInvitation.switchMap {
            var isLoading = it.status== Status.LOADING
            MutableLiveData(isLoading)
        }
        invitationDetail = invitationFromIntent.switchMap {
            invitationFromIntent.value?.let {
                repository.getInvitationDetail(it.invitation_id)
            }?: AbsentLiveData.create()
        }
        loadingDetail = invitationDetail.switchMap {
            var isLoading = it.status == Status.LOADING
            MutableLiveData(isLoading)
        }
    }
    fun postInvitation(invitation: Invitation?){
        invitationFromIntent.value = invitation
    }
    fun validateInvitation(){
        val invitation = invitationDetail.value?.data
        invitation?.status = InvitationStatus.ATTENDED.toString()
        invitation?.let {
            invitationMutable.value = it
        }
    }
}