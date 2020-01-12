package id.shobrun.stikieventorganizer.ui.myparticipants

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import id.shobrun.stikieventorganizer.models.Resource
import id.shobrun.stikieventorganizer.models.entity.Participant
import id.shobrun.stikieventorganizer.models.network.ParticipantsResponse
import id.shobrun.stikieventorganizer.repository.ParticipantRepository
import id.shobrun.stikieventorganizer.utils.AbsentLiveData
import id.shobrun.stikieventorganizer.utils.FakeData.fakeParticipants
import javax.inject.Inject

class MyParticipantsViewModel @Inject constructor(repository: ParticipantRepository) : ViewModel() {
    private var userId : MutableLiveData<Int> = MutableLiveData()
    val participantsLiveData : LiveData<Resource<List<Participant>,ParticipantsResponse>>
    init {
        participantsLiveData = userId.switchMap {
            userId.value?.let {
                repository.getMyParticipants(it)
            }?: AbsentLiveData.create()
        }
    }
    fun postUserId(id : Int){
        userId.postValue(id)
    }
}