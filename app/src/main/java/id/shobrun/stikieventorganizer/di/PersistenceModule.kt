package id.shobrun.stikieventorganizer.di

import android.app.Application
import dagger.Module
import dagger.Provides
import id.shobrun.stikieventorganizer.room.AppDatabase
import javax.inject.Singleton

@Module
class PersistenceModule {
    @Singleton
    @Provides
    internal fun provideRoomInstance(application: Application) =
        AppDatabase.getInstance(application)
}