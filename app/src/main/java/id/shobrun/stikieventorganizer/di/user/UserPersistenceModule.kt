package id.shobrun.stikieventorganizer.di.user

import dagger.Module
import dagger.Provides
import id.shobrun.stikieventorganizer.room.AppDatabase

@Module
class UserPersistenceModule {
    @Provides
    fun provideUserDao(appDatabase: AppDatabase) = appDatabase.userDao()
}