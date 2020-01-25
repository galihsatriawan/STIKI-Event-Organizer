package id.ac.stiki.doleno.stikieventorganizer.ui.profile

import androidx.lifecycle.ViewModel
import id.ac.stiki.doleno.stikieventorganizer.repository.UserRepository
import id.ac.stiki.doleno.stikieventorganizer.utils.SharedPref
import javax.inject.Inject

class ProfileViewModel @Inject constructor(val repository: UserRepository, sharedPref: SharedPref) :
    ViewModel() {
    // TODO: Implement the ViewModel
}
