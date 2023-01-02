package fr.systemathicdev.intentmanager.adapter.output

import android.content.Context
import android.content.Intent
import android.net.Uri
import fr.systemathicdev.commons.OutputAdapter
import fr.systemathicdev.commons.OutputAdapterScopeMain
import fr.systemathicdev.commons.OutputAdapterScopeWorker
import fr.systemathicdev.intentmanager.port.output.OpenUrlPort
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalIntentAdapter @Inject constructor(
    override val outputAdapterScopeMain: OutputAdapterScopeMain,
    override val outputAdapterScopeWorker: OutputAdapterScopeWorker,
    private val intentFactory: IntentFactory
) : OutputAdapter, OpenUrlPort {

    override suspend fun openUrl(context: Context, url: String) =
        withContext(outputAdapterScopeMain.coroutineContext) {
            intentFactory.create(Intent.ACTION_VIEW).apply {
                data = Uri.parse(url)
            }.let {
                context.startActivity(Intent.createChooser(it,""))
            }
        }
}