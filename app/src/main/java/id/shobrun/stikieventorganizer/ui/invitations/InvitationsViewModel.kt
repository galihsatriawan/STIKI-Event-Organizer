package id.shobrun.stikieventorganizer.ui.invitations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import id.shobrun.stikieventorganizer.models.Resource
import id.shobrun.stikieventorganizer.models.Status
import id.shobrun.stikieventorganizer.models.entity.Invitation
import id.shobrun.stikieventorganizer.models.network.InvitationsResponse
import id.shobrun.stikieventorganizer.repository.InvitationRepository
import id.shobrun.stikieventorganizer.utils.AbsentLiveData
import id.shobrun.stikieventorganizer.utils.FakeData.fakeInvitations
import id.shobrun.stikieventorganizer.utils.SharedPref
import id.shobrun.stikieventorganizer.utils.SharedPref.Companion.PREFS_USER_EMAIL
import javax.inject.Inject

class InvitationsViewModel @Inject constructor(repository : InvitationRepository,sharedPref: SharedPref): ViewModel() {
    private val participantEmail : MutableLiveData<String> = MutableLiveData()
    val invitationsLiveData : LiveData<Resource<List<Invitation>,InvitationsResponse>>
    val loading : LiveData<Boolean>
    init {

        invitationsLiveData = participantEmail.switchMap {
            participantEmail.value?.let {
                repository.getMyInvitation(it)
            }?:AbsentLiveData.create()
        }
        loading = invitationsLiveData.switchMap {
            var isLoading = it.status == Status.LOADING
            MutableLiveData(isLoading)
        }
        postParticipantEmail(sharedPref.getValue(PREFS_USER_EMAIL,""))
    }

    fun postParticipantEmail(email : String){
        this.participantEmail.value = email
    }
}