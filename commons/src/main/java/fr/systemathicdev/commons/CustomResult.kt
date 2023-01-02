package fr.systemathicdev.commons

import timber.log.Timber
import java.lang.Exception
import java.util.concurrent.atomic.AtomicBoolean

sealed class CustomResult<T> {
    data class Success<T>(val data: T) : CustomResult<T>()
    class Error<T>(val throwable: Throwable) : CustomResult<T>()
    class Loading<T> : CustomResult<T>()
    class Idle<T> : CustomResult<T>()

    private val consumed = AtomicBoolean(false)

    fun consume(block: () -> Unit){
        if(!consumed.getAndSet(true)){
            block()
        }
    }
}