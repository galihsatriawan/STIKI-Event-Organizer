package id.ac.stiki.doleno.stikieventorganizer.di.event.detail

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import id.ac.stiki.doleno.stikieventorganizer.di.ViewModelKey
import id.ac.stiki.doleno.stikieventorganizer.ui.myevents.detail.ParticipantEventViewModel


@Module
abstract class ParticipantEventViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ParticipantEventViewModel::class)
    abstract fun bindParticipantEventViewModel(participantEventViewModel: ParticipantEventViewModel): ViewModel
}