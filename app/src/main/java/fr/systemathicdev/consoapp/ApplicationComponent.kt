package fr.systemathicdev.consoapp

import dagger.Component
import fr.systemathicdev.core.products.config.ProductModule
import fr.systemathicdev.feature.FeatureSubComponent
import fr.systemathicdev.intentmanager.config.IntentManagerModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        ProductModule::class,
        IntentManagerModule::class,
    ]
)

interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)
    fun featureSubComponentBuilder() : FeatureSubComponent.Builder
}