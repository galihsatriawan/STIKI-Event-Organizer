package id.ac.stiki.doleno.stikieventorganizer.di.event.detail

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import id.ac.stiki.doleno.stikieventorganizer.di.ViewModelKey
import id.ac.stiki.doleno.stikieventorganizer.ui.myevents.detail.ParticipantSelectionViewModel

@Module
abstract class ParticipantSelectionViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ParticipantSelectionViewModel::class)
    abstract fun bindParticipantSelectionVM(participantSelectionViewModel: ParticipantSelectionViewModel): ViewModel
}