package id.ac.stiki.doleno.stikieventorganizer.ui.myevents

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import id.ac.stiki.doleno.stikieventorganizer.models.Resource
import id.ac.stiki.doleno.stikieventorganizer.models.Status
import id.ac.stiki.doleno.stikieventorganizer.models.entity.Event
import id.ac.stiki.doleno.stikieventorganizer.models.network.EventsResponse
import id.ac.stiki.doleno.stikieventorganizer.repository.EventRepository
import id.ac.stiki.doleno.stikieventorganizer.utils.AbsentLiveData
import id.ac.stiki.doleno.stikieventorganizer.utils.SharedPref
import id.ac.stiki.doleno.stikieventorganizer.utils.SharedPref.Companion.PREFS_USER_ID
import javax.inject.Inject

class MyEventsViewModel @Inject constructor(repository: EventRepository, sharedPref: SharedPref) :
    ViewModel() {

    private val userId = MutableLiveData<Int>()
    val eventsLiveData: LiveData<Resource<List<Event>, EventsResponse>>
    val loading: LiveData<Boolean>

    init {

        eventsLiveData = userId.switchMap {
            userId.value?.let {
                repository.getMyEvents(it)
            } ?: AbsentLiveData.create()
        }
        loading = eventsLiveData.switchMap {
            var isLoading = it.status == Status.LOADING
            MutableLiveData(isLoading)
        }
        postUserId(sharedPref.getValue(PREFS_USER_ID, -1))
    }

    fun postUserId(id: Int) {
        userId.value = id
    }
}