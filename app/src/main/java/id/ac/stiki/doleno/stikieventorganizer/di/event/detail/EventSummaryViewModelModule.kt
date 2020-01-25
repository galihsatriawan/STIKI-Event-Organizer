package id.ac.stiki.doleno.stikieventorganizer.di.event.detail

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import id.ac.stiki.doleno.stikieventorganizer.di.ViewModelKey
import id.ac.stiki.doleno.stikieventorganizer.ui.myevents.detail.EventSummaryViewModel


@Module
abstract class EventSummaryViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(EventSummaryViewModel::class)
    abstract fun bindEventSummaryVM(eventSummaryViewModel: EventSummaryViewModel): ViewModel
}