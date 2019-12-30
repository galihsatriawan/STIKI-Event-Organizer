package id.shobrun.stikieventorganizer.di.event.detail

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import id.shobrun.stikieventorganizer.di.ViewModelKey
import id.shobrun.stikieventorganizer.ui.myevents.detail.EventDetailViewModel

@Module
abstract class EventDetailViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(EventDetailViewModel::class)
    abstract fun bindEventDetailVM(eventDetailViewModel: EventDetailViewModel) : ViewModel
}