package fr.systemathicdev.intentmanager.port.input

import android.content.Context

interface OpenUrlUseCase {
    suspend fun openUrl(context: Context, url: String)
}