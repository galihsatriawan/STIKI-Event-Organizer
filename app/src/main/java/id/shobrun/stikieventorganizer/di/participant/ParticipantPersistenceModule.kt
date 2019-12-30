package id.shobrun.stikieventorganizer.di.participant

import dagger.Module
import dagger.Provides
import id.shobrun.stikieventorganizer.room.AppDatabase

@Module
class ParticipantPersistenceModule {
    @Provides
    fun provideParticipantDao(appDatabase: AppDatabase) = appDatabase.participantDao()
}