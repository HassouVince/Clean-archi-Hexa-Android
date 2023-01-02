package fr.systemathicdev.commons

import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext

abstract class AdapterScope(private val context: CoroutineContext) : CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = context
}