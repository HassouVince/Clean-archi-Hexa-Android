package fr.systemathicdev.commons

import fr.systemathicdev.commons.extensions.*
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.lang.Exception

@RunWith(MockitoJUnitRunner::class)
class FlowExtensionsTest {

    @Test
    fun `adapt flat in resource should catch exception`() = runBlocking<Unit> {
        val successFlow = flowOf(listOf(1, 2)).mapInResult()
        val result = successFlow.mapFlatInResult { flow<CustomResult<Int>> { throw Exception() }.mapInResult() }
            .toList().last()
        assertThat(
            result
        ).isInstanceOf(CustomResult.Error::class.java)
    }
}