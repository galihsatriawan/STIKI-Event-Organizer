package id.ac.stiki.doleno.stikieventorganizer.di.event.detail

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import id.ac.stiki.doleno.stikieventorganizer.di.ViewModelKey
import id.ac.stiki.doleno.stikieventorganizer.ui.myevents.detail.EventDetailMainViewModel
import javax.inject.Singleton

@Module
abstract class EventMainViewModelModule{
    @Binds
    @IntoMap
    @ViewModelKey(EventDetailMainViewModel::class)
    abstract fun bindEventMainVM (eventDetailMainViewModel: EventDetailMainViewModel) : ViewModel
}