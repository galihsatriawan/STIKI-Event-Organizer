package id.shobrun.stikieventorganizer.ui.myevents.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import id.shobrun.stikieventorganizer.models.Resource
import id.shobrun.stikieventorganizer.models.Status
import id.shobrun.stikieventorganizer.models.entity.Event
import id.shobrun.stikieventorganizer.models.entity.EventStatus
import id.shobrun.stikieventorganizer.models.network.EventsResponse
import id.shobrun.stikieventorganizer.repository.EventRepository
import id.shobrun.stikieventorganizer.utils.AbsentLiveData
import id.shobrun.stikieventorganizer.utils.Helper.getUniqueID
import id.shobrun.stikieventorganizer.utils.SharedPref
import id.shobrun.stikieventorganizer.utils.SharedPref.Companion.PREFS_USER_EMAIL
import id.shobrun.stikieventorganizer.utils.SharedPref.Companion.PREFS_USER_ID
import id.shobrun.stikieventorganizer.utils.SharedPref.Companion.PREFS_USER_USERNAME
import timber.log.Timber
import javax.inject.Inject

class EventDetailViewModel @Inject constructor(
    repository: EventRepository,
    val sharedPref: SharedPref
) : ViewModel() {
    private val eventId = MutableLiveData<String>()
    private val event: LiveData<Resource<Event, EventsResponse>>
    private var isNewEvent: Boolean = false
    var isUpdateLocation = MutableLiveData(false)
    val loading: LiveData<Boolean>
    val loadingUpdate: LiveData<Boolean>
    val isSuccessLoad = MutableLiveData<Boolean>()
    val isSuccess= MutableLiveData<Boolean>()
    val eventIdNew = MutableLiveData<String>()
    /**
     * Text Input
     */
    val eventName = MutableLiveData<String>()
    val eventDescription = MutableLiveData<String>()
    val eventDate = MutableLiveData<String>()
    val eventLocation = MutableLiveData<String>()
    val eventLinkMaps = MutableLiveData<String>()
    val eventCp = MutableLiveData<String>()
    val eventStatus = MutableLiveData<String>()
    val eventLatitude = MutableLiveData<Double>()
    val eventLongitude = MutableLiveData<Double>()

    private val _snackbarText = MutableLiveData<String>()
    val snackbarText: LiveData<String> = _snackbarText
    val eventMutable = MutableLiveData<Event>()
    val eventAction: LiveData<Resource<Event, EventsResponse>>

    init {
        event = eventId.switchMap {
            eventId.value?.let {
                repository.getEventDetail(it)
            } ?: AbsentLiveData.create()
        }
        loading = event.switchMap {
            val isLoading = it.status == Status.LOADING && !isNewEvent
            if (!isLoading) {
                if(!isNewEvent)
                    onEventLoaded(it.data)
            }
            MutableLiveData(isLoading)
        }
        eventAction = eventMutable.switchMap {
            eventMutable.value?.let {
                if (isNewEvent)
                    repository.insertEvent(it)
                else
                    repository.updateEvent(it)
            } ?: AbsentLiveData.create()
        }
        loadingUpdate = eventAction.switchMap {
            val isLoading = it.status == Status.LOADING
            if (!isLoading){
                if(it.status == Status.ERROR) _snackbarText.value = "Please Check Your Connection"
                else {
                    _snackbarText.value = it.message ?: it.additionalData?.message
                    isSuccess.value = true
                    eventId.value = eventMutable.value?.event_id
                    eventIdNew.value = eventId.value
                    isNewEvent = false
                }
            }
            MutableLiveData(isLoading)
        }

    }

    private fun onEventLoaded(event: Event?) {
        eventName.value = event?.event_name
        eventDescription.value = event?.event_description
        eventDate.value = event?.event_date
        eventLinkMaps.value = event?.event_map_location
        eventLocation.value = event?.event_location
        eventCp.value = event?.event_cp
        eventLatitude.value = event?.event_latitude
        eventLongitude.value = event?.event_longitude
        isSuccessLoad.value = true
    }

    fun saveEvent() {
        val currentName = eventName.value
        val currentDesc = eventDescription.value
        val currentDate = eventDate.value
        val currentLink = eventLinkMaps.value
        val currentLocation = eventLocation.value
        val currentCp = eventCp.value
        val currentLatitude = eventLatitude.value
        val currentLongitude = eventLongitude.value
        if (currentName.isNullOrEmpty() || currentDesc.isNullOrEmpty() || currentDate.isNullOrEmpty() || currentLocation.isNullOrEmpty() || currentCp.isNullOrEmpty()) {
            _snackbarText.value = "Please fill completely"
            return
        }
        if (isNewEvent) {
            val user_id = sharedPref.getValue(PREFS_USER_ID, -1)
            val user_username = sharedPref.getValue(PREFS_USER_USERNAME, "")
            val user_email = sharedPref.getValue(PREFS_USER_EMAIL, "")
            val eventId = getUniqueID("$user_id")
            val eventNew = Event(
                eventId,
                user_id,
                user_username,
                user_email,
                currentName,
                currentDesc,
                currentDate,
                currentLocation,
                currentLink,
                currentLatitude,
                currentLongitude,
                currentCp,
                EventStatus.ACTIVE.toString(),
                0,
                0
            )
            insertEvent(eventNew)
        } else {
            val eventTemp = this.event.value?.data!!
            eventTemp.event_name = currentName
            eventTemp.event_description = currentDesc
            eventTemp.event_date = currentDate
            eventTemp.event_cp = currentCp
            eventTemp.event_map_location = currentLink
            eventTemp.event_location = currentLocation
            eventTemp.event_latitude = currentLatitude
            eventTemp.event_longitude = currentLongitude
            updateEvent(eventTemp)
        }
    }

    fun postEventId(id: String?) {
        isNewEvent = id == null
        isUpdateLocation.value = isNewEvent
        this.eventId.value = id ?: "-1"
    }

    private fun insertEvent(event: Event) {
        eventMutable.value = event
    }

    private fun updateEvent(event: Event) {
        eventMutable.value = event
    }
    fun actionUpdateLocation(){
        isUpdateLocation.value = true
    }

}