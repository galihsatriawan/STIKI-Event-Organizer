package id.shobrun.stikieventorganizer.di.invitation.list

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import id.shobrun.stikieventorganizer.di.ViewModelKey
import id.shobrun.stikieventorganizer.ui.invitations.InvitationsViewModel

@Module
abstract class InvitationFragmentViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(InvitationsViewModel::class)
    abstract fun bindInvitationViewModel(invitationsViewModel: InvitationsViewModel): ViewModel
}