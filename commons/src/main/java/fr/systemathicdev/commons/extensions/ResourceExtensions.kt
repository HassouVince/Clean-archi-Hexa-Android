package fr.systemathicdev.commons.extensions

import fr.systemathicdev.commons.CustomResult

fun <T> CustomResult<T>.getSuccessDataOrNull(): T? {
    return (this as? CustomResult.Success<T>)?.data
}

fun <T> CustomResult<T>.onSuccess(action: (T) -> Unit) = when(this){
    is CustomResult.Success -> apply { action(data) }
    is CustomResult.Error -> this
    is CustomResult.Loading -> this
    is CustomResult.Idle -> this
}

fun <T> CustomResult<T>.onError(action: (Throwable) -> Unit) = when(this){
    is CustomResult.Success -> this
    is CustomResult.Error -> apply { action(throwable) }
    is CustomResult.Loading -> this
    is CustomResult.Idle -> this
}

fun <T> CustomResult<T>.onLoading(action: () -> Unit) = when(this){
    is CustomResult.Success -> this
    is CustomResult.Error -> this
    is CustomResult.Loading -> apply { action() }
    is CustomResult.Idle -> this
}