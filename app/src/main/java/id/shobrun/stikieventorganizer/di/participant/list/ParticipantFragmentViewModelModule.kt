package id.shobrun.stikieventorganizer.di.participant.list

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import id.shobrun.stikieventorganizer.di.ViewModelKey
import id.shobrun.stikieventorganizer.ui.myparticipants.MyParticipantsViewModel

@Module
abstract class ParticipantFragmentViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MyParticipantsViewModel::class)
    abstract fun bindParticipantsViewModel(myParticipantsViewModel: MyParticipantsViewModel): ViewModel
}