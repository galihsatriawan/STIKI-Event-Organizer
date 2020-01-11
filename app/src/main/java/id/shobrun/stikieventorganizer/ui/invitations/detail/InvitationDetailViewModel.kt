package id.shobrun.stikieventorganizer.ui.invitations.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import id.shobrun.stikieventorganizer.models.Resource
import id.shobrun.stikieventorganizer.models.entity.Event
import id.shobrun.stikieventorganizer.models.entity.Invitation
import id.shobrun.stikieventorganizer.repository.EventRepository
import id.shobrun.stikieventorganizer.repository.InvitationRepository
import id.shobrun.stikieventorganizer.utils.AbsentLiveData
import javax.inject.Inject

class InvitationDetailViewModel @Inject constructor(invitationRepository : InvitationRepository, eventRepository: EventRepository): ViewModel(){
    private val invitationId = MutableLiveData<Int>()
    private val eventId = MutableLiveData<String>()
    private val event : LiveData<Resource<Event>>
    private val invitation : LiveData<Resource<Invitation>>
    init {
        event = eventId.switchMap {
            eventId.value?.let {
                eventRepository.getEventDetail(it)
            }?:AbsentLiveData.create()
        }
        invitation = invitationId.switchMap {
            invitationId.value?.let {
                invitationRepository.getInvitationDetail(it)
            }?:AbsentLiveData.create()
        }
    }


    fun postInvitationId(id : Int){
        this.invitationId.value = id
    }
    fun postEventId(id : String){
        this.eventId.value = id
    }
}