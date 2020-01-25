package id.ac.stiki.doleno.stikieventorganizer.di.invitation.detail

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import id.ac.stiki.doleno.stikieventorganizer.di.ViewModelKey
import id.ac.stiki.doleno.stikieventorganizer.ui.invitations.detail.InvitationDetailViewModel

@Module
abstract class InvitationDetailViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(InvitationDetailViewModel::class)
    abstract fun bindInvitationDetailVM(invitationDetailViewModel: InvitationDetailViewModel): ViewModel
}