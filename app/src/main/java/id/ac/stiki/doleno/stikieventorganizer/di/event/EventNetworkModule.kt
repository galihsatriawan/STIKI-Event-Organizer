package id.ac.stiki.doleno.stikieventorganizer.di.event

import dagger.Module
import dagger.Provides
import id.ac.stiki.doleno.stikieventorganizer.api.EventApi
import retrofit2.Retrofit

@Module
class EventNetworkModule {
    @Provides
    fun provideEventApi(retrofit: Retrofit) = retrofit.create(EventApi::class.java)
}