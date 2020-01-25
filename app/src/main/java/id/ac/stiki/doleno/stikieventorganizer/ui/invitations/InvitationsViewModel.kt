package id.ac.stiki.doleno.stikieventorganizer.ui.invitations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import id.ac.stiki.doleno.stikieventorganizer.models.Resource
import id.ac.stiki.doleno.stikieventorganizer.models.Status
import id.ac.stiki.doleno.stikieventorganizer.models.entity.Invitation
import id.ac.stiki.doleno.stikieventorganizer.models.network.InvitationsResponse
import id.ac.stiki.doleno.stikieventorganizer.repository.InvitationRepository
import id.ac.stiki.doleno.stikieventorganizer.utils.AbsentLiveData
import id.ac.stiki.doleno.stikieventorganizer.utils.SharedPref
import id.ac.stiki.doleno.stikieventorganizer.utils.SharedPref.Companion.PREFS_USER_EMAIL
import timber.log.Timber
import javax.inject.Inject

class InvitationsViewModel @Inject constructor(
    repository: InvitationRepository,
    sharedPref: SharedPref
) : ViewModel() {
    private val participantEmail: MutableLiveData<String> = MutableLiveData()
    val invitationsLiveData: LiveData<Resource<List<Invitation>, InvitationsResponse>>
    val loading: LiveData<Boolean>

    init {

        invitationsLiveData = participantEmail.switchMap {
            participantEmail.value?.let {
                repository.getMyInvitation(it)
            } ?: AbsentLiveData.create()
        }
        loading = invitationsLiveData.switchMap {
            var isLoading = it.status == Status.LOADING
            MutableLiveData(isLoading)
        }
        Timber.d(sharedPref.getValue(PREFS_USER_EMAIL, ""))
        postParticipantEmail(sharedPref.getValue(PREFS_USER_EMAIL, ""))
    }

    fun postParticipantEmail(email: String) {
        this.participantEmail.value = email
    }
}