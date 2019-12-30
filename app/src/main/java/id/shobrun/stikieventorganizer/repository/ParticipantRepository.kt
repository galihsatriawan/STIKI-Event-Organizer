package id.shobrun.stikieventorganizer.repository

import androidx.lifecycle.LiveData
import id.shobrun.stikieventorganizer.AppExecutors
import id.shobrun.stikieventorganizer.api.ApiResponse
import id.shobrun.stikieventorganizer.api.ParticipantApi
import id.shobrun.stikieventorganizer.models.Resource
import id.shobrun.stikieventorganizer.models.entity.Participant
import id.shobrun.stikieventorganizer.models.network.ParticipantsResponse
import id.shobrun.stikieventorganizer.room.ParticipantDao
import timber.log.Timber
import javax.inject.Inject

class ParticipantRepository @Inject constructor(private val appExecutors: AppExecutors, private val apiService : ParticipantApi,private val localDB : ParticipantDao){

    companion object{
        val TAG = this.javaClass.name
    }
    fun getParticipantDetail(id : Int) : LiveData<Participant>{
        return localDB.getDetailParticipant(id)
    }
    fun getMyParticipants(id : Int) : LiveData<Resource<List<Participant>>> {
        return object : NetworkBoundRepository<List<Participant>,ParticipantsResponse>(appExecutors) {
            override fun saveFetchData(items: ParticipantsResponse) {
                val participants = items.result
                if(participants!=null){
                    localDB.inserts(items.result)
                }

            }

            override fun shouldFetch(data: List<Participant>?): Boolean {
                return true
            }

            override fun loadFromDb(): LiveData<List<Participant>> {
                return localDB.getMyParticipants()
            }

            override fun fetchService(): LiveData<ApiResponse<ParticipantsResponse>> {
                return apiService.getMyParticipants(id)
            }

            override fun onFetchFailed(message: String?) {
                Timber.d("$TAG $message")
            }

        }.asLiveData()
    }
}