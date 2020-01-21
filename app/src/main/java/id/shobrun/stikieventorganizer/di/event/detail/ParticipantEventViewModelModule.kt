package id.shobrun.stikieventorganizer.di.event.detail

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import id.shobrun.stikieventorganizer.di.ViewModelKey
import id.shobrun.stikieventorganizer.ui.myevents.detail.ParticipantEventViewModel


@Module
abstract class ParticipantEventViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ParticipantEventViewModel::class)
    abstract fun bindParticipantEventViewModel(participantEventViewModel: ParticipantEventViewModel): ViewModel
}