package id.shobrun.stikieventorganizer.di.participant

import dagger.Module
import dagger.Provides
import id.shobrun.stikieventorganizer.AppExecutors
import id.shobrun.stikieventorganizer.api.ParticipantApi
import id.shobrun.stikieventorganizer.repository.ParticipantRepository
import id.shobrun.stikieventorganizer.room.ParticipantDao

@Module
class ParticipantRepositoryModule {

    @Provides
    fun provideParticipantRepository(appExecutors: AppExecutors,apiService : ParticipantApi,localDB : ParticipantDao)
            = ParticipantRepository(appExecutors,apiService,localDB)
}