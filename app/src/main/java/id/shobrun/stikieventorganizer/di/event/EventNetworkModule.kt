package id.shobrun.stikieventorganizer.di.event

import dagger.Module
import dagger.Provides
import id.shobrun.stikieventorganizer.api.EventApi
import retrofit2.Retrofit

@Module
class EventNetworkModule {
    @Provides
    fun provideEventApi(retrofit: Retrofit) = retrofit.create(EventApi::class.java)
}