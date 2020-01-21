package id.shobrun.stikieventorganizer.di.event.detail

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import id.shobrun.stikieventorganizer.di.ViewModelKey
import id.shobrun.stikieventorganizer.ui.myevents.detail.EventSummaryViewModel


@Module
abstract class EventSummaryViewModelModule{

    @Binds
    @IntoMap
    @ViewModelKey(EventSummaryViewModel::class)
    abstract fun bindEventSummaryVM(eventSummaryViewModel: EventSummaryViewModel) : ViewModel
}