package id.shobrun.stikieventorganizer.ui.myparticipants

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import id.shobrun.stikieventorganizer.models.Resource
import id.shobrun.stikieventorganizer.models.entity.Participant
import id.shobrun.stikieventorganizer.utils.AbsentLiveData
import id.shobrun.stikieventorganizer.utils.FakeData.fakeParticipants
import javax.inject.Inject

class MyParticipantsViewModel @Inject constructor() : ViewModel() {
    private var userId : MutableLiveData<Int> = MutableLiveData()
    val participantsLiveData : LiveData<Resource<List<Participant>>>
    init {
        participantsLiveData = userId.switchMap {
            userId.value?.let {
                val data = Resource.success(fakeParticipants())
                val mutable = MutableLiveData<Resource<List<Participant>>>()
                mutable.postValue(data)
                val liveData : LiveData<Resource<List<Participant>>> = mutable
                liveData
            }?: AbsentLiveData.create()
        }
    }
    fun postUserId(id : Int){
        userId.postValue(id)
    }
}