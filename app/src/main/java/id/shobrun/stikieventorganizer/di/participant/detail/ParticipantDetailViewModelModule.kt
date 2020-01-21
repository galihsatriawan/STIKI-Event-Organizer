package id.shobrun.stikieventorganizer.di.participant.detail

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import id.shobrun.stikieventorganizer.di.ViewModelKey
import id.shobrun.stikieventorganizer.ui.myparticipants.detail.ParticipantDetailViewModel

@Module
abstract class ParticipantDetailViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ParticipantDetailViewModel::class)
    abstract fun bindParticipantDetailVM(participantDetailViewModel: ParticipantDetailViewModel): ViewModel
}