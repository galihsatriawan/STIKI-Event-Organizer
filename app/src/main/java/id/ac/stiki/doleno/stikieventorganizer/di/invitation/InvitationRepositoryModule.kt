package id.ac.stiki.doleno.stikieventorganizer.di.invitation

import dagger.Module
import dagger.Provides
import id.ac.stiki.doleno.stikieventorganizer.AppExecutors
import id.ac.stiki.doleno.stikieventorganizer.api.InvitationApi
import id.ac.stiki.doleno.stikieventorganizer.repository.InvitationRepository
import id.ac.stiki.doleno.stikieventorganizer.room.InvitationDao

@Module
class InvitationRepositoryModule {
    @Provides
    fun provideInvitationRepository(
        appExecutors: AppExecutors,
        apiService: InvitationApi,
        localDB: InvitationDao
    ) = InvitationRepository(appExecutors, apiService, localDB)
}