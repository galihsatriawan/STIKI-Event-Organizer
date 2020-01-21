package id.shobrun.stikieventorganizer.di.user.profile

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import id.shobrun.stikieventorganizer.di.ViewModelKey
import id.shobrun.stikieventorganizer.ui.profile.ProfileViewModel

@Module
abstract class ProfileViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileVM(profileViewModel: ProfileViewModel): ViewModel
}