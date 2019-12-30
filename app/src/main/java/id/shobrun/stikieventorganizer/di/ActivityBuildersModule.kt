package id.shobrun.stikieventorganizer.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import id.shobrun.stikieventorganizer.di.invitation.InvitationNetworkModule
import id.shobrun.stikieventorganizer.di.invitation.InvitationPersistenceModule
import id.shobrun.stikieventorganizer.di.invitation.InvitationRepositoryModule
import id.shobrun.stikieventorganizer.di.invitation.detail.InvitationDetailModule
import id.shobrun.stikieventorganizer.di.invitation.detail.InvitationDetailViewModelModule
import id.shobrun.stikieventorganizer.ui.invitations.detail.InvitationDetailActivity
import id.shobrun.stikieventorganizer.ui.invitations.detail.InvitationDetailViewModel


@Module
abstract class ActivityBuildersModule {
    @ContributesAndroidInjector(
        modules =[
            InvitationDetailModule::class,
            InvitationDetailViewModelModule::class,
            InvitationNetworkModule::class,
            InvitationPersistenceModule::class,
            InvitationRepositoryModule::class
        ]
    )
    abstract fun injectInvitationDetailActivity() : InvitationDetailActivity
}