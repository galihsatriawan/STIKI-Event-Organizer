package id.ac.stiki.doleno.stikieventorganizer.di.event

import dagger.Module
import dagger.Provides
import id.ac.stiki.doleno.stikieventorganizer.room.AppDatabase

@Module
class EventPersistenceModule {
    @Provides
    fun provideEventDao(appDatabase: AppDatabase) = appDatabase.eventDao()
}