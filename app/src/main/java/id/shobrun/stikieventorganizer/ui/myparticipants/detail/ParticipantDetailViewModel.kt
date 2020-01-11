package id.shobrun.stikieventorganizer.ui.myparticipants.detail

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import id.shobrun.stikieventorganizer.models.Resource
import id.shobrun.stikieventorganizer.models.Status
import id.shobrun.stikieventorganizer.models.entity.Participant
import id.shobrun.stikieventorganizer.repository.ParticipantRepository
import id.shobrun.stikieventorganizer.utils.AbsentLiveData
import id.shobrun.stikieventorganizer.utils.Helper
import id.shobrun.stikieventorganizer.utils.Helper.getUniqueID
import timber.log.Timber
import javax.inject.Inject


class ParticipantDetailViewModel @Inject constructor(private val repository : ParticipantRepository): ViewModel(){
    private var isNewParticipant = false
    private val participantId : MutableLiveData<String> = MutableLiveData()
    val participant : LiveData<Resource<Participant>>
    val participantName = MutableLiveData<String>()
    val participantTelp = MutableLiveData<String>()
    val participantEmail = MutableLiveData<String>()
    val participantAddress = MutableLiveData<String>()
    val loading : LiveData<Boolean>
    init {
        participant = participantId.switchMap {
            participantId.value?.let {
                repository.getParticipantDetail(it)
            }?:AbsentLiveData.create()
        }
        loading = participant.switchMap {
            val state = it.status == Status.LOADING && !isNewParticipant
            val mutable : MutableLiveData<Boolean> = MutableLiveData()
            onParticipantLoaded(it.data)
            mutable.postValue(state)
            mutable
        }

    }
    companion object{
        val TAG = ParticipantDetailViewModel.javaClass.name
    }
    private fun onParticipantLoaded(participant: Participant?){
        Timber.d("$TAG ${participant?.participant_name} ${Helper.getTimeStamp()}")
        participantName.value = participant?.participant_name
        participantAddress.value = participant?.participant_address
        participantEmail.value = participant?.participant_email
        participantTelp.value = participant?.participant_telp
    }
    fun postParticipantId(id : String?){
        isNewParticipant = id==null
        this.participantId.value = id?:"-1"
    }
    fun saveParticipant(){
        val currentName = participantName.value
        val currentAddress = participantAddress.value
        val currentEmail = participantEmail.value
        val currentTelp = participantTelp.value
        if(currentName.isNullOrEmpty() || currentAddress.isNullOrEmpty() || currentEmail.isNullOrEmpty() || currentTelp.isNullOrEmpty()){
            /**
             * Message
             */
            Timber.d("$currentName - $currentAddress - $currentEmail - $currentTelp null edittext")
            return
        }
        if(isNewParticipant){
            val user_id =1
            val user_username= "galih"
            val user_email ="galih@gmail.com"
            val participant = Participant(getUniqueID("$user_id"),currentName,currentEmail,user_id,user_username,user_email,currentTelp,currentAddress)
            insertParticipant(participant)
        }else{
            val participant = this.participant.value?.data!!
            participant.participant_name = currentName
            participant.participant_address = currentAddress
            participant.participant_email = currentEmail
            participant.participant_telp = currentTelp
            updateParticipant(participant)
        }
    }
    private fun insertParticipant (participant: Participant){
        repository.insertObj(participant)

    }
    private fun updateParticipant(participant: Participant){
        repository.updateObj(participant)
        postParticipantId(participant.participant_id)
    }
}