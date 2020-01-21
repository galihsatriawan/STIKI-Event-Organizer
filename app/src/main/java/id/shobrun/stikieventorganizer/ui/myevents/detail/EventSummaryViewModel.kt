package id.shobrun.stikieventorganizer.ui.myevents.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import id.shobrun.stikieventorganizer.models.Resource
import id.shobrun.stikieventorganizer.models.Status
import id.shobrun.stikieventorganizer.models.entity.Event
import id.shobrun.stikieventorganizer.models.network.EventsResponse
import id.shobrun.stikieventorganizer.repository.EventRepository
import id.shobrun.stikieventorganizer.utils.AbsentLiveData
import id.shobrun.stikieventorganizer.utils.SharedPref
import javax.inject.Inject

class EventSummaryViewModel @Inject constructor(val repository: EventRepository, val sharedPref: SharedPref): ViewModel() {
    var isNewEvent = MutableLiveData<Boolean>()
    val eventLiveData : LiveData<Resource<Event,EventsResponse>>
    val eventIdLiveData = MutableLiveData<String>()
    val loading :LiveData<Boolean>
    private val _snackbarText =MutableLiveData<String>()
    val snackbarText :LiveData<String> = _snackbarText
    init {
        eventLiveData = eventIdLiveData.switchMap {
            eventIdLiveData.value?.let {
                repository.getEventDetail(it)
            }?:AbsentLiveData.create()
        }
        loading = eventLiveData.switchMap{
            val isLoading = it.status == Status.LOADING
            MutableLiveData(isLoading)
        }
    }
    fun postEventId(eventId: String?){
        isNewEvent.value = eventId==null
        if(eventId!=null) eventIdLiveData.postValue(eventId?:"-1")
    }

}
