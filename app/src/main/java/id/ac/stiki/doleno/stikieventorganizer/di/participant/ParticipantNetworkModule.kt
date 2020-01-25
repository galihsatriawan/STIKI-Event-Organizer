package id.ac.stiki.doleno.stikieventorganizer.di.participant

import dagger.Module
import dagger.Provides
import id.ac.stiki.doleno.stikieventorganizer.api.ParticipantApi
import retrofit2.Retrofit

@Module
class ParticipantNetworkModule {
    @Provides
    fun provideParticipantApi(retrofit: Retrofit): ParticipantApi {
        return retrofit.create(ParticipantApi::class.java)
    }
}