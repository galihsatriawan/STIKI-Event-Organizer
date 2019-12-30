package id.shobrun.stikieventorganizer.ui.myevents

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import id.shobrun.stikieventorganizer.models.Resource
import id.shobrun.stikieventorganizer.models.entity.Event
import id.shobrun.stikieventorganizer.repository.EventRepository
import id.shobrun.stikieventorganizer.utils.AbsentLiveData
import javax.inject.Inject

class MyEventsViewModel @Inject constructor(repository: EventRepository) : ViewModel() {
    private val userId = MutableLiveData<Int>()
    val eventsLiveData : LiveData<Resource<List<Event>>>

    init {
        eventsLiveData = userId.switchMap {
            userId.value?.let {
                repository.getMyEvents(it)
            }?:AbsentLiveData.create()
        }
    }

    fun postUserId(id : Int){
        userId.value = id
    }
}