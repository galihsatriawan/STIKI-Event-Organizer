package id.shobrun.stikieventorganizer.ui.invitations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import id.shobrun.stikieventorganizer.models.Resource
import id.shobrun.stikieventorganizer.models.entity.Invitation
import id.shobrun.stikieventorganizer.utils.AbsentLiveData
import id.shobrun.stikieventorganizer.utils.FakeData.fakeInvitations
import javax.inject.Inject

class InvitationsViewModel @Inject constructor(): ViewModel() {
    private val participantEmail : MutableLiveData<String> = MutableLiveData()
    val invitationsLiveData : LiveData<Resource<List<Invitation>>>

    init {
        invitationsLiveData = participantEmail.switchMap {
            participantEmail.value?.let {
                val resource = Resource.success(fakeInvitations())
                val result = MutableLiveData<Resource<List<Invitation>>>(resource)
                val liveData : LiveData<Resource<List<Invitation>>> = result
                liveData
            }?:AbsentLiveData.create()
        }
    }

    fun postParticipantEmail(email : String){
        this.participantEmail.value = email
    }
}