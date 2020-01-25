package id.ac.stiki.doleno.stikieventorganizer.di.invitation.list

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import id.ac.stiki.doleno.stikieventorganizer.di.ViewModelKey
import id.ac.stiki.doleno.stikieventorganizer.ui.invitations.InvitationsViewModel

@Module
abstract class InvitationFragmentViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(InvitationsViewModel::class)
    abstract fun bindInvitationViewModel(invitationsViewModel: InvitationsViewModel): ViewModel
}