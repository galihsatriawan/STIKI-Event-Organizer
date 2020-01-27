package id.ac.stiki.doleno.stikieventorganizer.ui.myevents.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import id.ac.stiki.doleno.stikieventorganizer.models.Resource
import id.ac.stiki.doleno.stikieventorganizer.models.Status
import id.ac.stiki.doleno.stikieventorganizer.models.entity.Invitation
import id.ac.stiki.doleno.stikieventorganizer.models.network.InvitationsResponse
import id.ac.stiki.doleno.stikieventorganizer.repository.InvitationRepository
import id.ac.stiki.doleno.stikieventorganizer.ui.adapter.RecyclerParticipantSelectionAdapter
import id.ac.stiki.doleno.stikieventorganizer.utils.AbsentLiveData
import id.ac.stiki.doleno.stikieventorganizer.utils.SharedPref
import id.ac.stiki.doleno.stikieventorganizer.utils.SharedPref.Companion.PREFS_USER_ID
import javax.inject.Inject

class ParticipantSelectionViewModel @Inject constructor(
    val repository: InvitationRepository,
    val sharedPref: SharedPref
) : ViewModel() {
    // TODO: Implement the ViewModel
    private var eventId: MutableLiveData<String> = MutableLiveData()
    val participantsLiveData: LiveData<Resource<List<Invitation>, InvitationsResponse>>
    val participantMutable: MutableLiveData<List<Invitation>> = MutableLiveData()
    val participantResAction: LiveData<Resource<List<Invitation>, InvitationsResponse>>
    lateinit var recyclerAdapter: RecyclerParticipantSelectionAdapter
    val loading: LiveData<Boolean>
    val loadingAction: LiveData<Boolean>
    val isSuccess = MutableLiveData<Boolean>()
    val _snackbarText = MutableLiveData<String>()
    var action = false
    val snackbarText: LiveData<String> = _snackbarText

    init {
        participantsLiveData = eventId.switchMap {
            eventId.value?.let {
                val data = repository.getAllParticipants(sharedPref.getValue(PREFS_USER_ID, -1), it)
                data
            } ?: AbsentLiveData.create()
        }
        loading = participantsLiveData.switchMap {
            var isLoading = it.status == Status.LOADING
            MutableLiveData(isLoading)
        }
        participantResAction = participantMutable.switchMap {
            participantMutable.value?.let {
                repository.updateEventParticipant(
                    sharedPref.getValue(PREFS_USER_ID, -1),
                    eventId.value!!,
                    it
                )
            } ?: AbsentLiveData.create()
        }
        loadingAction = participantResAction.switchMap {
            val isLoading = it.status == Status.LOADING
            if (!isLoading) {
                if (it.status == Status.SUCCESS) isSuccess.value = true
                if (!isLoading){
                    if(it.status == Status.ERROR) _snackbarText.value = "Please Check Your Connection"
                    else if(action){
                        _snackbarText.value = it.message ?: it.additionalData?.message
                        action = false
                    }
                }
            }
            MutableLiveData(isLoading)
        }
    }

    fun postEventId(id: String?) {
        eventId.postValue(id ?: "-1")
    }

    fun addParticipantSelection() {
        action = true
        val selection = ArrayList<Invitation>()
        for (i in recyclerAdapter.items) {
//            Timber.d("Selection -${i.participant_id}- ${i.is_invited}")
            if (i.is_invited ?: false) selection.add(i)
        }
        participantMutable.value = selection
    }
}
