package id.ac.stiki.doleno.stikieventorganizer.di

import android.app.Application
import dagger.Module
import dagger.Provides
import id.ac.stiki.doleno.stikieventorganizer.utils.SharedPref
import id.ac.stiki.doleno.stikieventorganizer.utils.Tools

@Module
class AppModule {
    @Provides
    fun provideSharedPref(application: Application) = SharedPref(application)

    @Provides
    fun provideTools(application: Application) = Tools(application)
}