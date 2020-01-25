package id.ac.stiki.doleno.stikieventorganizer.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import id.ac.stiki.doleno.stikieventorganizer.di.event.EventNetworkModule
import id.ac.stiki.doleno.stikieventorganizer.di.event.EventPersistenceModule
import id.ac.stiki.doleno.stikieventorganizer.di.event.EventRepositoryModule
import id.ac.stiki.doleno.stikieventorganizer.di.event.detail.*
import id.ac.stiki.doleno.stikieventorganizer.di.event.list.EventFragmentModule
import id.ac.stiki.doleno.stikieventorganizer.di.event.list.EventFragmentViewModelModule
import id.ac.stiki.doleno.stikieventorganizer.di.invitation.InvitationNetworkModule
import id.ac.stiki.doleno.stikieventorganizer.di.invitation.InvitationPersistenceModule
import id.ac.stiki.doleno.stikieventorganizer.di.invitation.InvitationRepositoryModule
import id.ac.stiki.doleno.stikieventorganizer.di.invitation.list.InvitationFragmentModule
import id.ac.stiki.doleno.stikieventorganizer.di.invitation.list.InvitationFragmentViewModelModule
import id.ac.stiki.doleno.stikieventorganizer.di.participant.ParticipantNetworkModule
import id.ac.stiki.doleno.stikieventorganizer.di.participant.ParticipantPersistenceModule
import id.ac.stiki.doleno.stikieventorganizer.di.participant.ParticipantRepositoryModule
import id.ac.stiki.doleno.stikieventorganizer.di.participant.list.ParticipantFragmentModule
import id.ac.stiki.doleno.stikieventorganizer.di.participant.list.ParticipantFragmentViewModelModule
import id.ac.stiki.doleno.stikieventorganizer.di.user.UserNetworkModule
import id.ac.stiki.doleno.stikieventorganizer.di.user.UserPersistenceModule
import id.ac.stiki.doleno.stikieventorganizer.di.user.UserRepositoryModule
import id.ac.stiki.doleno.stikieventorganizer.di.user.profile.ProfileModule
import id.ac.stiki.doleno.stikieventorganizer.di.user.profile.ProfileViewModelModule
import id.ac.stiki.doleno.stikieventorganizer.ui.invitations.InvitationsFragment
import id.ac.stiki.doleno.stikieventorganizer.ui.myevents.MyEventsFragment
import id.ac.stiki.doleno.stikieventorganizer.ui.myevents.detail.EventDetailFragment
import id.ac.stiki.doleno.stikieventorganizer.ui.myevents.detail.EventSummaryFragment
import id.ac.stiki.doleno.stikieventorganizer.ui.myevents.detail.ParticipantEventFragment
import id.ac.stiki.doleno.stikieventorganizer.ui.myparticipants.MyParticipantsFragment
import id.ac.stiki.doleno.stikieventorganizer.ui.profile.ProfileFragment

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
    abstract fun injectParticipantFragment(): MyParticipantsFragment

    @ContributesAndroidInjector(
        modules = [
            InvitationFragmentModule::class,
            InvitationFragmentViewModelModule::class,
            InvitationNetworkModule::class,
            InvitationPersistenceModule::class,
            InvitationRepositoryModule::class
        ]
    )
    abstract fun injectInvitationFragment(): InvitationsFragment

    @ContributesAndroidInjector(
        modules = [
            EventFragmentModule::class,
            EventFragmentViewModelModule::class,
            EventNetworkModule::class,
            EventPersistenceModule::class,
            EventRepositoryModule::class
        ]
    )
    abstract fun injectEventFragment(): MyEventsFragment

    @ContributesAndroidInjector(
        modules = [
            EventDetailModule::class,
            EventDetailViewModelModule::class,
            EventNetworkModule::class,
            EventPersistenceModule::class,
            EventRepositoryModule::class
        ]
    )
    abstract fun injectEventDetailFragment(): EventDetailFragment

    @ContributesAndroidInjector(
        modules = [
            EventSummaryModule::class,
            EventSummaryViewModelModule::class,
            EventNetworkModule::class,
            EventPersistenceModule::class,
            EventRepositoryModule::class
        ]
    )
    abstract fun injectEventSummaryFragment(): EventSummaryFragment

    @ContributesAndroidInjector(
        modules = [
            ParticipantEventModule::class,
            ParticipantEventViewModelModule::class,
            InvitationNetworkModule::class,
            InvitationPersistenceModule::class,
            InvitationRepositoryModule::class
        ]
    )
    abstract fun injectParticipantEventFragment(): ParticipantEventFragment

    @ContributesAndroidInjector(
        modules = [
            ProfileModule::class,
            ProfileViewModelModule::class,
            UserNetworkModule::class,
            UserPersistenceModule::class,
            UserRepositoryModule::class
        ]
    )
    abstract fun injectProfileFragment(): ProfileFragment

}