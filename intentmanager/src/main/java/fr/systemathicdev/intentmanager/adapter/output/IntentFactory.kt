package fr.systemathicdev.intentmanager.adapter.output

import android.content.Intent
import javax.inject.Inject

class IntentFactory @Inject constructor() {
    fun create(action: String) : Intent {
        return Intent(action)
    }
}