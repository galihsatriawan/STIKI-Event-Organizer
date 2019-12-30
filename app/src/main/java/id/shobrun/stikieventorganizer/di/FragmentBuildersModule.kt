package id.shobrun.stikieventorganizer.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import id.shobrun.stikieventorganizer.di.event.EventNetworkModule
import id.shobrun.stikieventorganizer.di.event.EventPersistenceModule
import id.shobrun.stikieventorganizer.di.event.EventRepositoryModule
import id.shobrun.stikieventorganizer.di.event.list.EventFragmentModule
import id.shobrun.stikieventorganizer.di.event.list.EventFragmentViewModelModule
import id.shobrun.stikieventorganizer.di.invitation.InvitationNetworkModule
import id.shobrun.stikieventorganizer.di.invitation.InvitationPersistenceModule
import id.shobrun.stikieventorganizer.di.invitation.InvitationRepositoryModule
import id.shobrun.stikieventorganizer.di.invitation.list.InvitationFragmentModule
import id.shobrun.stikieventorganizer.di.invitation.list.InvitationFragmentViewModelModule
import id.shobrun.stikieventorganizer.di.participant.ParticipantNetworkModule
import id.shobrun.stikieventorganizer.di.participant.ParticipantPersistenceModule
import id.shobrun.stikieventorganizer.di.participant.ParticipantRepositoryModule
import id.shobrun.stikieventorganizer.di.participant.list.ParticipantFragmentModule
import id.shobrun.stikieventorganizer.di.participant.list.ParticipantFragmentViewModelModule
import id.shobrun.stikieventorganizer.ui.invitations.InvitationsFragment
import id.shobrun.stikieventorganizer.ui.myevents.MyEventsFragment
import id.shobrun.stikieventorganizer.ui.myparticipants.MyParticipantsFragment

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector(
        modules = [
            ParticipantFragmentModule::class,
            ParticipantFragmentViewModelModule::class,
            ParticipantNetworkModule::class,
            ParticipantPersistenceModule::class,
            ParticipantRepositoryModule::class
        ]
    )
    abstract fun injectParticipantFragment() : MyParticipantsFragment

    @ContributesAndroidInjector(
        modules = [
            InvitationFragmentModule::class,
            InvitationFragmentViewModelModule::class,
            InvitationNetworkModule::class,
            InvitationPersistenceModule::class,
            InvitationRepositoryModule::class
        ]
    )
    abstract fun injectInvitationFragment() : InvitationsFragment

    @ContributesAndroidInjector(
        modules = [
            EventFragmentModule::class,
            EventFragmentViewModelModule::class,
            EventNetworkModule::class,
            EventPersistenceModule::class,
            EventRepositoryModule::class
    ])
    abstract fun injectEventFragment() : MyEventsFragment
}