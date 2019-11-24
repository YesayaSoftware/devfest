package software.yesaya.devfest

import android.app.Application
import com.facebook.stetho.Stetho
import com.jakewharton.threetenabp.AndroidThreeTen
import timber.log.Timber

class DevFestApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        AndroidThreeTen.init(this)

        Timber.plant(Timber.DebugTree())

        initStetho()
    }

    private fun initStetho() {
        if (BuildConfig.DEBUG)
            Stetho.initializeWithDefaults(this)
    }
}