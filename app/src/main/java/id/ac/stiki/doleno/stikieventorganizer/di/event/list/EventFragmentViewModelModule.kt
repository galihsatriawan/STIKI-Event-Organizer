package id.ac.stiki.doleno.stikieventorganizer.di.event.list

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import id.ac.stiki.doleno.stikieventorganizer.di.ViewModelKey
import id.ac.stiki.doleno.stikieventorganizer.ui.myevents.MyEventsViewModel

@Module
abstract class EventFragmentViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MyEventsViewModel::class)
    abstract fun bindMyEventsViewModel(myEventsViewModel: MyEventsViewModel): ViewModel
}