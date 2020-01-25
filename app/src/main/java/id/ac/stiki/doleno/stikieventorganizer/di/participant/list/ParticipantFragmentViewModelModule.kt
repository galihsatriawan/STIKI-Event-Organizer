package id.ac.stiki.doleno.stikieventorganizer.di.participant.list

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import id.ac.stiki.doleno.stikieventorganizer.di.ViewModelKey
import id.ac.stiki.doleno.stikieventorganizer.ui.myparticipants.MyParticipantsViewModel

@Module
abstract class ParticipantFragmentViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MyParticipantsViewModel::class)
    abstract fun bindParticipantsViewModel(myParticipantsViewModel: MyParticipantsViewModel): ViewModel
}