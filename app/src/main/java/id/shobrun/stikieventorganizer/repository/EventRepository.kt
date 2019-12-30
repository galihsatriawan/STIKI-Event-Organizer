package id.shobrun.stikieventorganizer.repository

import androidx.lifecycle.LiveData
import id.shobrun.stikieventorganizer.AppExecutors
import id.shobrun.stikieventorganizer.api.ApiResponse
import id.shobrun.stikieventorganizer.api.EventApi
import id.shobrun.stikieventorganizer.models.entity.Event
import id.shobrun.stikieventorganizer.models.network.EventsResponse
import id.shobrun.stikieventorganizer.models.network.InvitationsResponse
import id.shobrun.stikieventorganizer.room.EventDao
import timber.log.Timber
import javax.inject.Inject

class EventRepository @Inject constructor(private val appExecutors: AppExecutors,private val apiService : EventApi, private val localDB : EventDao) {
    companion object{
        val TAG = this.javaClass.name
    }
    fun getMyEvents(id : Int) = object : NetworkBoundRepository<List<Event>,EventsResponse>(appExecutors) {
        override fun saveFetchData(items: EventsResponse) {
            val events = items.result
            if(events!=null){
                localDB.inserts(events)
            }
        }

        override fun shouldFetch(data: List<Event>?): Boolean {
            return true
        }

        override fun loadFromDb(): LiveData<List<Event>> {
            return localDB.getMyEvents()
        }

        override fun fetchService(): LiveData<ApiResponse<EventsResponse>> {
            return apiService.getMyEvents(id)
        }

        override fun onFetchFailed(message: String?) {
            Timber.d("$TAG $message")
        }
    }.asLiveData()

}