package id.ac.stiki.doleno.stikieventorganizer.di.invitation

import dagger.Module
import dagger.Provides
import id.ac.stiki.doleno.stikieventorganizer.room.AppDatabase

@Module
class InvitationPersistenceModule {
    @Provides
    fun provideInvitationDao(appDatabase: AppDatabase) = appDatabase.invitationDao()
}