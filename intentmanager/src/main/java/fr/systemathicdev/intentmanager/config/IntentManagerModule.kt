package fr.systemathicdev.intentmanager.config

import dagger.Binds
import dagger.Module
import fr.systemathicdev.intentmanager.adapter.output.LocalIntentAdapter
import fr.systemathicdev.intentmanager.port.IntentManagerService
import fr.systemathicdev.intentmanager.port.input.OpenUrlUseCase
import fr.systemathicdev.intentmanager.port.output.OpenUrlPort

@Module
abstract class IntentManagerModule {

    @Binds
    abstract fun provideOpenUrlUseCase(intentManagerService: IntentManagerService) : OpenUrlUseCase

    @Binds
    abstract fun provideOpenUrlPort(localIntentAdapter: LocalIntentAdapter) : OpenUrlPort
}