package id.shobrun.stikieventorganizer.di

import dagger.Module
import dagger.Provides
import id.shobrun.stikieventorganizer.api.LiveDataCallAdapterFactory
import id.shobrun.stikieventorganizer.api.RequestInterceptor
import id.shobrun.stikieventorganizer.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule{
    @Singleton
    @Provides
    internal fun provideOkHttpClient() : OkHttpClient{
        return OkHttpClient().newBuilder()
            .addInterceptor(RequestInterceptor())
            .build()
    }

    @Singleton
    @Provides
    internal fun provideRetrofit(okHttpClient: OkHttpClient) : Retrofit{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .client(okHttpClient)
            .build()
    }


}