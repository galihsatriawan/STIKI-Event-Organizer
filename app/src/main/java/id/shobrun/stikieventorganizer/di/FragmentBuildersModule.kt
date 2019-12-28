package id.shobrun.stikieventorganizer.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import id.shobrun.stikieventorganizer.di.participant.ParticipantFragmentModule
import id.shobrun.stikieventorganizer.di.participant.ParticipantFragmentViewModelModule
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
}