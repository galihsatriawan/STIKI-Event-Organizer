package id.shobrun.stikieventorganizer.repository

import androidx.lifecycle.LiveData
import id.shobrun.stikieventorganizer.AppExecutors
import id.shobrun.stikieventorganizer.api.ApiResponse
import id.shobrun.stikieventorganizer.api.ParticipantApi
import id.shobrun.stikieventorganizer.models.Resource
import id.shobrun.stikieventorganizer.models.entity.Participant
import id.shobrun.stikieventorganizer.models.network.ParticipantsResponse
import id.shobrun.stikieventorganizer.repository.local.ILocalSource
import id.shobrun.stikieventorganizer.room.ParticipantDao
import timber.log.Timber
import javax.inject.Inject

class ParticipantRepository @Inject constructor(private val appExecutors: AppExecutors, private val apiService : ParticipantApi,private val localDB : ParticipantDao) : ILocalSource{

    companion object{
        val TAG = this.javaClass.name
    }
    fun getParticipantDetail(id : String) : LiveData<Resource<Participant>>{
        return object : NetworkBoundRepository<Participant,ParticipantsResponse>(appExecutors){
            override fun saveFetchData(items: ParticipantsResponse) {
                if(items.result.isNotEmpty()){
                    val item = items.result[0]
                    Timber.d("$TAG Service ${item.participant_name}")
                    localDB.insert(item)
                }
            }

            override fun shouldFetch(data: Participant?): Boolean {
                return true
            }

            override fun loadFromDb(): LiveData<Participant> {
                val item = localDB.getDetailParticipant(id)
                return item
            }

            override fun fetchService(): LiveData<ApiResponse<ParticipantsResponse>> {
                return apiService.getDetailParticipant(id)
            }

            override fun onFetchFailed(message: String?) {
                Timber.d("$TAG $message")
            }

        } .asLiveData()
    }
    fun getMyParticipants(id : Int) : LiveData<Resource<List<Participant>>> {
        return object : NetworkBoundRepository<List<Participant>,ParticipantsResponse>(appExecutors) {
            override fun saveFetchData(items: ParticipantsResponse) {
                val participants = items.result
                if(participants.isNotEmpty()){
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
    fun insertObj(participant: Participant){
        localDB.insert(participant)
        apiService.addParticipant(participant)
    }
    fun updateObj(participant: Participant){
        localDB.update(participant)
        apiService.updateParticipant(participant)
    }
    override fun <T> insertsLocal(obj: List<T>) {
        val participants = obj as List<Participant>
        localDB.inserts(participants)
    }

    override fun <T> insertLocal(obj: T) {
        val participant = obj as Participant
        Timber.d("${participant.participant_name}")
        localDB.insert(participant)
    }

    override fun <T> updateLocal(obj: T): Int {
        val participant = obj as Participant
        return localDB.update(participant)
    }

    override fun <T> deleteLocal(obj: T) {
        val participant = obj as Participant
        localDB.delete(participant.participant_id)
    }
}