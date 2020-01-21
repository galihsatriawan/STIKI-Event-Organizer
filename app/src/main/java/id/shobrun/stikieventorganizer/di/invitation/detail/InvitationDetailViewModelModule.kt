package id.shobrun.stikieventorganizer.di.invitation.detail

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import id.shobrun.stikieventorganizer.di.ViewModelKey
import id.shobrun.stikieventorganizer.ui.invitations.detail.InvitationDetailViewModel

@Module
abstract class InvitationDetailViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(InvitationDetailViewModel::class)
    abstract fun bindInvitationDetailVM(invitationDetailViewModel: InvitationDetailViewModel): ViewModel
}