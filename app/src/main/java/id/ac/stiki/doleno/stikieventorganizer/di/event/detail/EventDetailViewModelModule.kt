package id.ac.stiki.doleno.stikieventorganizer.di.event.detail

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import id.ac.stiki.doleno.stikieventorganizer.di.ViewModelKey
import id.ac.stiki.doleno.stikieventorganizer.ui.myevents.detail.EventDetailViewModel

@Module
abstract class EventDetailViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(EventDetailViewModel::class)
    abstract fun bindEventDetailVM(eventDetailViewModel: EventDetailViewModel): ViewModel
}