package id.shobrun.stikieventorganizer.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import id.shobrun.stikieventorganizer.di.event.EventNetworkModule
import id.shobrun.stikieventorganizer.di.event.EventPersistenceModule
import id.shobrun.stikieventorganizer.di.event.EventRepositoryModule
import id.shobrun.stikieventorganizer.di.event.detail.EventDetailModule
import id.shobrun.stikieventorganizer.di.event.detail.EventDetailViewModelModule
import id.shobrun.stikieventorganizer.di.invitation.InvitationNetworkModule
import id.shobrun.stikieventorganizer.di.invitation.InvitationPersistenceModule
import id.shobrun.stikieventorganizer.di.invitation.InvitationRepositoryModule
import id.shobrun.stikieventorganizer.di.invitation.detail.InvitationDetailModule
import id.shobrun.stikieventorganizer.di.invitation.detail.InvitationDetailViewModelModule
import id.shobrun.stikieventorganizer.ui.invitations.detail.InvitationDetailActivity
import id.shobrun.stikieventorganizer.ui.invitations.detail.InvitationDetailViewModel
import id.shobrun.stikieventorganizer.ui.myevents.detail.EventDetailActivity


@Module
abstract class ActivityBuildersModule {
    @ContributesAndroidInjector(
        modules =[
            InvitationDetailModule::class,
            InvitationDetailViewModelModule::class,
            InvitationNetworkModule::class,
            InvitationPersistenceModule::class,
            InvitationRepositoryModule::class,
            EventNetworkModule::class,
            EventPersistenceModule::class,
            EventRepositoryModule::class
        ]
    )
    abstract fun injectInvitationDetailActivity() : InvitationDetailActivity

    @ContributesAndroidInjector(
        modules =[
            EventDetailModule::class,
            EventDetailViewModelModule::class,
            EventNetworkModule::class,
            EventPersistenceModule::class,
            EventRepositoryModule::class
        ]
    )
    abstract fun injectEventDetailActivity() : EventDetailActivity
}