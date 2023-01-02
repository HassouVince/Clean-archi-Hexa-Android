package fr.systemathicdev.consoapp

import android.app.Application
import android.os.StrictMode
import fr.systemathicdev.consoapp.ApplicationModule
import fr.systemathicdev.feature.FeatureSubComponent
import fr.systemathicdev.feature.FeatureSubComponentProvider
import timber.log.Timber

class ConsoApplication :
    Application(),
    FeatureSubComponentProvider
{
    val appComponent : ApplicationComponent = DaggerApplicationComponent
        .builder()
        .applicationModule(ApplicationModule(this))
        .build()

    private val featureSubComponent : FeatureSubComponent by lazy {
        appComponent.featureSubComponentBuilder().build()
    }

    override fun onCreate() {
        super.onCreate()

        if(BuildConfig.DEBUG){
            Timber.plant(
                Timber.DebugTree()
            )
            StrictMode.setThreadPolicy(
                StrictMode.ThreadPolicy.Builder()
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectAll()
                    .penaltyLog()
                    .build()
            )
            StrictMode.setVmPolicy(
                StrictMode.VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects()
                    .detectLeakedClosableObjects()
                    .penaltyLog()
                    .build()
            )
        }
    }

    override fun provideFeatureSubComponent(): FeatureSubComponent {
        return featureSubComponent
    }
}