package id.shobrun.stikieventorganizer.di.event

import dagger.Module
import dagger.Provides
import id.shobrun.stikieventorganizer.AppExecutors
import id.shobrun.stikieventorganizer.api.EventApi
import id.shobrun.stikieventorganizer.repository.EventRepository
import id.shobrun.stikieventorganizer.room.EventDao

@Module
class EventRepositoryModule {
    @Provides
    fun provideEventRepository(
        appExecutors: AppExecutors,
        apiService: EventApi,
        localDB: EventDao
    ) = EventRepository(appExecutors, apiService, localDB)
}