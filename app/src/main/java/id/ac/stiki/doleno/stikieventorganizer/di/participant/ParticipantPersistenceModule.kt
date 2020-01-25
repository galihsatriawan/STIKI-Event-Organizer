package id.ac.stiki.doleno.stikieventorganizer.di.participant

import dagger.Module
import dagger.Provides
import id.ac.stiki.doleno.stikieventorganizer.room.AppDatabase

@Module
class ParticipantPersistenceModule {
    @Provides
    fun provideParticipantDao(appDatabase: AppDatabase) = appDatabase.participantDao()
}