package id.shobrun.stikieventorganizer.ui.myevents.detail

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
import id.shobrun.stikieventorganizer.utils.SharedPref
import javax.inject.Inject

class ParticipantSelectionViewModel @Inject constructor(val repository: InvitationRepository, val sharedPref: SharedPref) : ViewModel() {
    // TODO: Implement the ViewModel
    private var eventId : MutableLiveData<String> = MutableLiveData()
    val participantsLiveData : LiveData<Resource<List<Invitation>, InvitationsResponse>>
    val loading : LiveData<Boolean>
    init {
        participantsLiveData = eventId.switchMap {
            eventId.value?.let {
                repository.getAllParticipants(it)
            }?: AbsentLiveData.create()
        }
        loading = participantsLiveData.switchMap {
            var isLoading = it.status == Status.LOADING
            MutableLiveData(isLoading)
        }
    }
    fun postEventId(id : String?){
        eventId.postValue(id?:"-1")
    }
}
