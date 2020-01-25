package id.ac.stiki.doleno.stikieventorganizer

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import id.ac.stiki.doleno.stikieventorganizer.di.AppComponent
import id.ac.stiki.doleno.stikieventorganizer.di.DaggerAppComponent
import timber.log.Timber

class SEOApplication : DaggerApplication() {
    private val component: AppComponent by lazy {
        DaggerAppComponent.builder()
            .application(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return component
    }

}