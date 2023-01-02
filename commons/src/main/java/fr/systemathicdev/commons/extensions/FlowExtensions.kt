package fr.systemathicdev.commons.extensions

import fr.systemathicdev.commons.CustomResult
import kotlinx.coroutines.flow.*
import java.lang.Exception

suspend fun <T> Flow<CustomResult<T>>.getLastSuccessDataOrNull(): T?{
    return this.lastOrNull()?.getSuccessDataOrNull()
}

fun <T> Flow<T>.mapInResult(): Flow<CustomResult<T>>{
    return safeFlow {
        CustomResult.Success(it)
    }.onStart { emit(CustomResult.Loading()) }
}

fun<T, S> Flow<T>.safeFlow(block : suspend (T) -> CustomResult<S>) : Flow<CustomResult<S>> {
    return flow {
        this@safeFlow
            .map { block(it) }
            .catch {
                emit(CustomResult.Error(it))
            }.collect{
                emit(it)
            }
    }
}

fun <T, S> Flow<CustomResult<T>>.mapFlatInResult(transform: suspend (value: T) -> Flow<CustomResult<S>>) : Flow<CustomResult<S>>{
    return this.flatMapLatest {
        when(it) {
            is CustomResult.Success -> {
                try{
                    transform(it.data)
                }catch (e : Exception){
                    flowOf(CustomResult.Error(e))
                }
            }
            is CustomResult.Error -> flowOf(CustomResult.Error(it.throwable))
            is CustomResult.Loading -> flowOf(CustomResult.Loading())
            is CustomResult.Idle -> flowOf(CustomResult.Idle())
        }
    }
}

