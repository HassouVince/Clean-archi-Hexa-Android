package fr.systemathicdev.intentmanager.port

import android.content.Context
import fr.systemathicdev.intentmanager.port.input.OpenUrlUseCase
import fr.systemathicdev.intentmanager.port.output.OpenUrlPort
import javax.inject.Inject

class IntentManagerService @Inject constructor(
    private val openUrlPort: OpenUrlPort
) : OpenUrlUseCase {
    override suspend fun openUrl(context: Context, url: String) {
        openUrlPort.openUrl(context,url)
    }
}