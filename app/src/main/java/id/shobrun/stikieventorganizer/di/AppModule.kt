package id.shobrun.stikieventorganizer.di

import android.app.Application
import dagger.Module
import dagger.Provides
import id.shobrun.stikieventorganizer.utils.SharedPref
import id.shobrun.stikieventorganizer.utils.Tools

@Module
class AppModule {
    @Provides
    fun provideSharedPref(application: Application) = SharedPref(application)

    @Provides
    fun provideTools(application: Application) = Tools(application)
}