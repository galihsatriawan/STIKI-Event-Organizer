package id.ac.stiki.doleno.stikieventorganizer.di.participant.detail

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import id.ac.stiki.doleno.stikieventorganizer.di.ViewModelKey
import id.ac.stiki.doleno.stikieventorganizer.ui.myparticipants.detail.ParticipantDetailViewModel

@Module
abstract class ParticipantDetailViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ParticipantDetailViewModel::class)
    abstract fun bindParticipantDetailVM(participantDetailViewModel: ParticipantDetailViewModel): ViewModel
}