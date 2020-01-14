package id.shobrun.stikieventorganizer.ui.myevents.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import id.shobrun.stikieventorganizer.models.Resource
import id.shobrun.stikieventorganizer.models.entity.Invitation
import id.shobrun.stikieventorganizer.models.entity.Participant
import id.shobrun.stikieventorganizer.models.network.InvitationsResponse
import id.shobrun.stikieventorganizer.models.network.ParticipantsResponse
import id.shobrun.stikieventorganizer.repository.EventRepository
import id.shobrun.stikieventorganizer.repository.InvitationRepository
import id.shobrun.stikieventorganizer.repository.ParticipantRepository
import id.shobrun.stikieventorganizer.utils.AbsentLiveData
import javax.inject.Inject

class ParticipantEventViewModel @Inject constructor(repository: InvitationRepository) : ViewModel() {
    private var isNewEvent = false
    private var eventId : MutableLiveData<String> = MutableLiveData()
    val participantsLiveData : LiveData<Resource<List<Invitation>,InvitationsResponse>>

    init {
        participantsLiveData = eventId.switchMap {
            eventId.value?.let {
                repository.getParticipantsEvent(it)
            }?: AbsentLiveData.create()
        }
    }
    fun postEventId(id : String?){
        isNewEvent = id==null
        eventId.postValue(id?:"-1")
    }
}
