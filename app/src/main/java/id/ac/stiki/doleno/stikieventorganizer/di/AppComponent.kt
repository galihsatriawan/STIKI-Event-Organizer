package id.ac.stiki.doleno.stikieventorganizer.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import id.ac.stiki.doleno.stikieventorganizer.SEOApplication
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityBuildersModule::class,
        FragmentBuildersModule::class,
        NetworkModule::class,
        PersistenceModule::class,
        ViewModelsFactoryModule::class
    ]
)
interface AppComponent : AndroidInjector<SEOApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder

        fun build(): AppComponent
    }
}