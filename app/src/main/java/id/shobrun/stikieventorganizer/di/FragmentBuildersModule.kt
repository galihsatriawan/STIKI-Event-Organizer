package id.shobrun.stikieventorganizer.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import id.shobrun.stikieventorganizer.di.invitation.list.InvitationFragmentModule
import id.shobrun.stikieventorganizer.di.invitation.list.InvitationFragmentViewModelModule
import id.shobrun.stikieventorganizer.di.participant.ParticipantFragmentModule
import id.shobrun.stikieventorganizer.di.participant.ParticipantFragmentViewModelModule
import id.shobrun.stikieventorganizer.ui.invitations.InvitationsFragment
import id.shobrun.stikieventorganizer.ui.myparticipants.MyParticipantsFragment

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector(
        modules = [
            ParticipantFragmentModule::class,
            ParticipantFragmentViewModelModule::class
        ]
    )
    abstract fun injectParticipantFragment() : MyParticipantsFragment

    @ContributesAndroidInjector(
        modules = [
            InvitationFragmentModule::class,
            InvitationFragmentViewModelModule::class
        ]
    )
    abstract fun injectInvitationFragment() : InvitationsFragment
}