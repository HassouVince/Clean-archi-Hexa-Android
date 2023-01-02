package fr.systemathicdev.core.products.port.input

import fr.systemathicdev.commons.CustomResult
import fr.systemathicdev.core.products.domain.Product
import kotlinx.coroutines.flow.Flow

interface GetProductsListUseCase {
    suspend fun getProductsList(): Flow<CustomResult<List<Product>>>
}