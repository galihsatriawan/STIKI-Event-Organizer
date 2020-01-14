package id.shobrun.stikieventorganizer.ui.profile

import androidx.lifecycle.ViewModel
import id.shobrun.stikieventorganizer.repository.UserRepository
import id.shobrun.stikieventorganizer.utils.SharedPref
import javax.inject.Inject

class ProfileViewModel @Inject constructor(val repository: UserRepository,sharedPref: SharedPref): ViewModel() {
    // TODO: Implement the ViewModel
}
