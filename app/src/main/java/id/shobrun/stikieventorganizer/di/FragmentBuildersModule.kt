package id.shobrun.stikieventorganizer.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import id.shobrun.stikieventorganizer.di.event.EventNetworkModule
import id.shobrun.stikieventorganizer.di.event.EventPersistenceModule
import id.shobrun.stikieventorganizer.di.event.EventRepositoryModule
import id.shobrun.stikieventorganizer.di.event.detail.EventDetailModule
import id.shobrun.stikieventorganizer.di.event.detail.EventDetailViewModelModule
import id.shobrun.stikieventorganizer.di.event.detail.ParticipantEventModule
import id.shobrun.stikieventorganizer.di.event.detail.ParticipantEventViewModelModule
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
import id.shobrun.stikieventorganizer.di.user.UserNetworkModule
import id.shobrun.stikieventorganizer.di.user.UserPersistenceModule
import id.shobrun.stikieventorganizer.di.user.UserRepositoryModule
import id.shobrun.stikieventorganizer.di.user.profile.ProfileModule
import id.shobrun.stikieventorganizer.di.user.profile.ProfileViewModelModule
import id.shobrun.stikieventorganizer.ui.invitations.InvitationsFragment
import id.shobrun.stikieventorganizer.ui.myevents.MyEventsFragment
import id.shobrun.stikieventorganizer.ui.myevents.detail.EventDetailFragment
import id.shobrun.stikieventorganizer.ui.myevents.detail.ParticipantEventFragment
import id.shobrun.stikieventorganizer.ui.myparticipants.MyParticipantsFragment
import id.shobrun.stikieventorganizer.ui.profile.ProfileFragment

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
    @ContributesAndroidInjector(
        modules =[
            EventDetailModule::class,
            EventDetailViewModelModule::class,
            EventNetworkModule::class,
            EventPersistenceModule::class,
            EventRepositoryModule::class
        ]
    )
    abstract fun injectEventDetailFragment() : EventDetailFragment

    @ContributesAndroidInjector(
        modules =[
            ParticipantEventModule::class,
            ParticipantEventViewModelModule::class,
            InvitationNetworkModule::class,
            InvitationPersistenceModule::class,
            InvitationRepositoryModule::class
        ]
    )
    abstract fun injectParticipantEventFragment() : ParticipantEventFragment

    @ContributesAndroidInjector(
        modules = [
            ProfileModule::class,
            ProfileViewModelModule::class,
            UserNetworkModule::class,
            UserPersistenceModule::class,
            UserRepositoryModule::class
        ]
    )
    abstract fun injectProfileFragment() : ProfileFragment

}