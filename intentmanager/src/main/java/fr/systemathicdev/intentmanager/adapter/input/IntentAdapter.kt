package fr.systemathicdev.intentmanager.adapter.input

import android.content.Context
import fr.systemathicdev.commons.InputAdapter
import fr.systemathicdev.commons.InputAdapterScope
import fr.systemathicdev.intentmanager.port.input.OpenUrlUseCase
import kotlinx.coroutines.withContext
import javax.inject.Inject

class IntentAdapter @Inject constructor(
    private val openUrlUseCase: OpenUrlUseCase,
    override val adapterScope: InputAdapterScope
) : InputAdapter{

    suspend fun openUrl(context: Context, url: String) =
        withContext(adapterScope.coroutineContext){
            openUrlUseCase.openUrl(context,url)
        }
}