package fr.systemathicdev.intentmanager.port.output

import android.content.Context

interface OpenUrlPort {
    suspend fun openUrl(context: Context,url: String)
}