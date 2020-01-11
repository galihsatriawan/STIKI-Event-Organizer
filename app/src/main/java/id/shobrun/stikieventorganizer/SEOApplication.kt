package id.shobrun.stikieventorganizer

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import id.shobrun.stikieventorganizer.di.AppComponent
import id.shobrun.stikieventorganizer.di.DaggerAppComponent
import timber.log.Timber

class SEOApplication : DaggerApplication(){
    private val component: AppComponent by lazy{
        DaggerAppComponent.builder()
            .application(this)
            .build()
    }
    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
        /**
         * ThreeTenAbp
         */
        AndroidThreeTen.init(this)
    }
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return component
    }

}