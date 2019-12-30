package id.shobrun.stikieventorganizer.di.event

import dagger.Module
import dagger.Provides
import id.shobrun.stikieventorganizer.room.AppDatabase

@Module
class EventPersistenceModule {
    @Provides
    fun provideEventDao(appDatabase: AppDatabase) = appDatabase.eventDao()
}