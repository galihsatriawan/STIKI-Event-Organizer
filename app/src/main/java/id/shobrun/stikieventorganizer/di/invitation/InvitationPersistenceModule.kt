package id.shobrun.stikieventorganizer.di.invitation

import dagger.Module
import dagger.Provides
import id.shobrun.stikieventorganizer.room.AppDatabase

@Module
class InvitationPersistenceModule {
    @Provides
    fun provideInvitationDao(appDatabase: AppDatabase) = appDatabase.invitationDao()
}