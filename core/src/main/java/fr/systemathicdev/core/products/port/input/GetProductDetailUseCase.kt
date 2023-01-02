package fr.systemathicdev.core.products.port.input

import fr.systemathicdev.commons.CustomResult
import fr.systemathicdev.core.products.domain.ProductDetail
import kotlinx.coroutines.flow.Flow

interface GetProductDetailUseCase {
    suspend fun getProductDetail(id: String): Flow<CustomResult<ProductDetail>>
}