package id.ac.stiki.doleno.stikieventorganizer.di.participant

import dagger.Module
import dagger.Provides
import id.ac.stiki.doleno.stikieventorganizer.AppExecutors
import id.ac.stiki.doleno.stikieventorganizer.api.ParticipantApi
import id.ac.stiki.doleno.stikieventorganizer.repository.ParticipantRepository
import id.ac.stiki.doleno.stikieventorganizer.room.ParticipantDao

@Module
class ParticipantRepositoryModule {

    @Provides
    fun provideParticipantRepository(
        appExecutors: AppExecutors,
        apiService: ParticipantApi,
        localDB: ParticipantDao
    ) = ParticipantRepository(appExecutors, apiService, localDB)
}