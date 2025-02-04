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
import id.shobrun.stikieventorganizer.ui.adapter.RecyclerParticipantSelectionAdapter
import id.shobrun.stikieventorganizer.utils.AbsentLiveData
import id.shobrun.stikieventorganizer.utils.SharedPref
import id.shobrun.stikieventorganizer.utils.SharedPref.Companion.PREFS_USER_ID
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
                    else _snackbarText.value = it.message ?: it.additionalData?.message
                }
            }
            MutableLiveData(isLoading)
        }
    }

    fun postEventId(id: String?) {
        eventId.postValue(id ?: "-1")
    }

    fun addParticipantSelection() {

        val selection = ArrayList<Invitation>()
        for (i in recyclerAdapter.items) {
//            Timber.d("Selection -${i.participant_id}- ${i.is_invited}")
            if (i.is_invited ?: false) selection.add(i)
        }
        participantMutable.value = selection
    }
}
