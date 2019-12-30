package id.shobrun.stikieventorganizer.ui.myevents.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import id.shobrun.stikieventorganizer.models.Resource
import id.shobrun.stikieventorganizer.models.entity.Event
import id.shobrun.stikieventorganizer.repository.EventRepository
import id.shobrun.stikieventorganizer.utils.AbsentLiveData
import javax.inject.Inject

class EventDetailViewModel @Inject constructor(repository: EventRepository): ViewModel(){
    private val eventId = MutableLiveData<Int>()
    private val event : LiveData<Resource<Event>>
    init {
        event = eventId.switchMap {
            eventId.value?.let {
                repository.getEventDetail(it)
            }?: AbsentLiveData.create()
        }
    }

    fun postEventId(id : Int){
        this.eventId.value = id
    }
}