package id.ac.stiki.doleno.stikieventorganizer.di.user

import dagger.Module
import dagger.Provides
import id.ac.stiki.doleno.stikieventorganizer.api.UserApi
import retrofit2.Retrofit

@Module
class UserNetworkModule {
    @Provides
    fun provideUserApi(retrofit: Retrofit) = retrofit.create(UserApi::class.java)
}