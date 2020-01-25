package id.ac.stiki.doleno.stikieventorganizer.di.event

import dagger.Module
import dagger.Provides
import id.ac.stiki.doleno.stikieventorganizer.AppExecutors
import id.ac.stiki.doleno.stikieventorganizer.api.EventApi
import id.ac.stiki.doleno.stikieventorganizer.repository.EventRepository
import id.ac.stiki.doleno.stikieventorganizer.room.EventDao

@Module
class EventRepositoryModule {
    @Provides
    fun provideEventRepository(
        appExecutors: AppExecutors,
        apiService: EventApi,
        localDB: EventDao
    ) = EventRepository(appExecutors, apiService, localDB)
}