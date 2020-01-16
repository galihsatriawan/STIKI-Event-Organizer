package id.shobrun.stikieventorganizer.ui.invitations.detail

import android.app.Dialog
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.google.gson.Gson
import id.shobrun.stikieventorganizer.models.Resource
import id.shobrun.stikieventorganizer.models.Status
import id.shobrun.stikieventorganizer.models.entity.Event
import id.shobrun.stikieventorganizer.models.entity.Invitation
import id.shobrun.stikieventorganizer.models.network.EventsResponse
import id.shobrun.stikieventorganizer.models.network.InvitationsResponse
import id.shobrun.stikieventorganizer.repository.EventRepository
import id.shobrun.stikieventorganizer.repository.InvitationRepository
import id.shobrun.stikieventorganizer.utils.AbsentLiveData
import id.shobrun.stikieventorganizer.utils.DialogTools
import id.shobrun.stikieventorganizer.utils.Helper.directionMaps
import javax.inject.Inject


class InvitationDetailViewModel @Inject constructor(invitationRepository : InvitationRepository, eventRepository: EventRepository): ViewModel(){
    private val invitationId = MutableLiveData<Int>()
    private val eventId = MutableLiveData<String>()
    val event : LiveData<Resource<Event,EventsResponse>>
    val invitation : LiveData<Resource<Invitation,InvitationsResponse>>
    val loadingEvent : LiveData<Boolean>
    val loadingInvitation : LiveData<Boolean>
    val actionQrCode = MutableLiveData<Boolean>()
    init {
        event = eventId.switchMap {
            eventId.value?.let {
                eventRepository.getEventDetail(it)
            }?:AbsentLiveData.create()
        }
        loadingEvent = event.switchMap {
            var isLoading = it.status==Status.LOADING
            MutableLiveData(isLoading)
        }
        invitation = invitationId.switchMap {
            invitationId.value?.let {
                invitationRepository.getInvitationDetail(it)
            }?:AbsentLiveData.create()
        }
        loadingInvitation = invitation.switchMap {
            var isLoading = it.status==Status.LOADING
            MutableLiveData(isLoading)
        }
    }


    fun postInvitationId(id : Int?){
        this.invitationId.value = id?:-1
    }
    fun postEventId(id : String?){
        this.eventId.value = id?:"-1"
    }
    fun directMaps(v:View){
        event.value?.let {
            val map = directionMaps(it.data!!.event_latitude!!,it.data!!.event_longitude!!)
            v.context.startActivity(map)
        }
    }
    fun showQRCode(){
        if(actionQrCode.value==null) actionQrCode.value = true
        else actionQrCode.value = !actionQrCode.value!!

    }
}