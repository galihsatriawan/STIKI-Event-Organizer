package id.shobrun.stikieventorganizer.di.event.detail

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import id.shobrun.stikieventorganizer.di.ViewModelKey
import id.shobrun.stikieventorganizer.ui.myevents.detail.ParticipantSelectionViewModel

@Module
abstract class ParticipantSelectionViewModelModule{
    @Binds
    @IntoMap
    @ViewModelKey(ParticipantSelectionViewModel::class)
    abstract fun bindParticipantSelectionVM (participantSelectionViewModel: ParticipantSelectionViewModel) : ViewModel
}