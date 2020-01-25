package id.ac.stiki.doleno.stikieventorganizer.di.user

import dagger.Module
import dagger.Provides
import id.ac.stiki.doleno.stikieventorganizer.AppExecutors
import id.ac.stiki.doleno.stikieventorganizer.api.UserApi
import id.ac.stiki.doleno.stikieventorganizer.repository.UserRepository
import id.ac.stiki.doleno.stikieventorganizer.room.UserDao

@Module
class UserRepositoryModule {

    @Provides
    fun provideUserRepository(apiService: UserApi, localDB: UserDao, appExecutors: AppExecutors) =
        UserRepository(apiService, localDB, appExecutors)
}