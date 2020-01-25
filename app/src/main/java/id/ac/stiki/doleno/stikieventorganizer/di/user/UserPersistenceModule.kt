package id.ac.stiki.doleno.stikieventorganizer.di.user

import dagger.Module
import dagger.Provides
import id.ac.stiki.doleno.stikieventorganizer.room.AppDatabase

@Module
class UserPersistenceModule {
    @Provides
    fun provideUserDao(appDatabase: AppDatabase) = appDatabase.userDao()
}