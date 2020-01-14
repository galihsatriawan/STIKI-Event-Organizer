package id.shobrun.stikieventorganizer.di.user

import dagger.Module
import dagger.Provides
import id.shobrun.stikieventorganizer.AppExecutors
import id.shobrun.stikieventorganizer.api.UserApi
import id.shobrun.stikieventorganizer.repository.UserRepository
import id.shobrun.stikieventorganizer.room.UserDao

@Module
class UserRepositoryModule{

    @Provides
    fun provideUserRepository (apiService: UserApi, localDB : UserDao,appExecutors: AppExecutors) = UserRepository(apiService,localDB, appExecutors)
}