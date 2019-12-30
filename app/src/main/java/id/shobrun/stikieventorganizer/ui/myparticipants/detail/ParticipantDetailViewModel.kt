package id.shobrun.stikieventorganizer.ui.myparticipants.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import id.shobrun.stikieventorganizer.models.entity.Participant
import id.shobrun.stikieventorganizer.repository.ParticipantRepository
import id.shobrun.stikieventorganizer.utils.AbsentLiveData
import javax.inject.Inject

class ParticipantDetailViewModel @Inject constructor(repository : ParticipantRepository): ViewModel(){
    private val participantId = MutableLiveData<Int>()
    private val participant : LiveData<Participant>

    init {
        participant = participantId.switchMap {
            participantId.value?.let {
                repository.getParticipantDetail(it)
            }?:AbsentLiveData.create()
        }
    }

    fun postParticipantId(id : Int){
        this.participantId.value = id
    }
}