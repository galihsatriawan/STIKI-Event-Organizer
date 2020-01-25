package id.ac.stiki.doleno.stikieventorganizer.di.invitation

import dagger.Module
import dagger.Provides
import id.ac.stiki.doleno.stikieventorganizer.api.InvitationApi
import retrofit2.Retrofit

@Module
class InvitationNetworkModule {
    @Provides
    internal fun provideInvitationApi(retrofit: Retrofit) =
        retrofit.create(InvitationApi::class.java)
}