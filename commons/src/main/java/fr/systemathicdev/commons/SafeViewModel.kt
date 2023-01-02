package fr.systemathicdev.commons

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

open class SafeViewModel(private val mutex: Mutex = Mutex()) : ViewModel() {
    fun <T> safeLaunch(block: suspend () -> T) = viewModelScope.launch {
        if (!mutex.isLocked) {
            mutex.withLock { block() }
        }
    }
}