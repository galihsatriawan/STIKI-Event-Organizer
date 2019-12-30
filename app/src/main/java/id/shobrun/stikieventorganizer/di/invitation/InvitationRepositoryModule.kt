package id.shobrun.stikieventorganizer.di.invitation

import dagger.Module
import dagger.Provides
import id.shobrun.stikieventorganizer.AppExecutors
import id.shobrun.stikieventorganizer.api.InvitationApi
import id.shobrun.stikieventorganizer.repository.InvitationRepository
import id.shobrun.stikieventorganizer.room.InvitationDao
import javax.inject.Singleton

@Module
class InvitationRepositoryModule {
    @Provides
    fun provideInvitationRepository(appExecutors: AppExecutors,apiService : InvitationApi,localDB : InvitationDao) = InvitationRepository(appExecutors,apiService,localDB)
}