package id.ac.stiki.doleno.stikieventorganizer.ui.myparticipants

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import id.ac.stiki.doleno.stikieventorganizer.models.Resource
import id.ac.stiki.doleno.stikieventorganizer.models.Status
import id.ac.stiki.doleno.stikieventorganizer.models.entity.Participant
import id.ac.stiki.doleno.stikieventorganizer.models.network.ParticipantsResponse
import id.ac.stiki.doleno.stikieventorganizer.repository.ParticipantRepository
import id.ac.stiki.doleno.stikieventorganizer.utils.AbsentLiveData
import id.ac.stiki.doleno.stikieventorganizer.utils.SharedPref
import id.ac.stiki.doleno.stikieventorganizer.utils.SharedPref.Companion.PREFS_USER_ID
import javax.inject.Inject

class MyParticipantsViewModel @Inject constructor(
    repository: ParticipantRepository,
    sharedPref: SharedPref
) : ViewModel() {

    private var userId: MutableLiveData<Int> = MutableLiveData()
    val participantsLiveData: LiveData<Resource<List<Participant>, ParticipantsResponse>>
    val loading: LiveData<Boolean>

    init {

        participantsLiveData = userId.switchMap {
            userId.value?.let {
                repository.getMyParticipants(it)
            } ?: AbsentLiveData.create()
        }
        loading = participantsLiveData.switchMap {
            var isLoading = it.status == Status.LOADING
            MutableLiveData(isLoading)
        }
        postUserId(sharedPref.getValue(PREFS_USER_ID, -1))
    }

    fun postUserId(id: Int) {
        userId.postValue(id)
    }
}