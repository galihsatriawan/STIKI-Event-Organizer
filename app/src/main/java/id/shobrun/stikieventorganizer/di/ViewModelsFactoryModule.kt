package id.shobrun.stikieventorganizer.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import id.shobrun.stikieventorganizer.factory.AppViewModelsFactory

@Module
abstract class ViewModelsFactoryModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: AppViewModelsFactory): ViewModelProvider.Factory
}