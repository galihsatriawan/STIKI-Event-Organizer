package id.shobrun.stikieventorganizer.di.user

import dagger.Module
import dagger.Provides
import id.shobrun.stikieventorganizer.api.UserApi
import retrofit2.Retrofit

@Module
class UserNetworkModule {
    @Provides
    fun provideUserApi(retrofit: Retrofit) = retrofit.create(UserApi::class.java)
}