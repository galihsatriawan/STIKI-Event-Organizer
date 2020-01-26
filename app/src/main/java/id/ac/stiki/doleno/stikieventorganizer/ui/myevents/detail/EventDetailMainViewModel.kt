package id.ac.stiki.doleno.stikieventorganizer.ui.myevents.detail

import androidx.lifecycle.ViewModel
import id.ac.stiki.doleno.stikieventorganizer.repository.EventRepository
import id.ac.stiki.doleno.stikieventorganizer.repository.InvitationRepository
import javax.inject.Inject

class EventDetailMainViewModel @Inject constructor(private val eventRepo:EventRepository, private  val invitationRepo: InvitationRepository): ViewModel(){

}