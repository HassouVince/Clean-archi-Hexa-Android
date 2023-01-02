package fr.systemathicdev.core.products.port

import fr.systemathicdev.commons.CustomResult
import fr.systemathicdev.commons.extensions.mapInResult
import fr.systemathicdev.core.products.domain.Product
import fr.systemathicdev.core.products.domain.ProductDetail
import fr.systemathicdev.core.products.port.input.GetProductDetailUseCase
import fr.systemathicdev.core.products.port.input.GetProductsListUseCase
import fr.systemathicdev.core.products.port.output.GetProductDetailPort
import fr.systemathicdev.core.products.port.output.GetProductsListPort
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductService @Inject constructor(
    private val getProductsListPort: GetProductsListPort,
    private val getProductDetailPort: GetProductDetailPort,
) : GetProductsListUseCase, GetProductDetailUseCase {

    override suspend fun getProductsList(): Flow<CustomResult<List<Product>>> =
        flow {
            emit(getProductsListPort.getProductsList())
        }.mapInResult()

    override suspend fun getProductDetail(id: String): Flow<CustomResult<ProductDetail>> =
        flow {
            emit(getProductDetailPort.getProductDetail(id))
        }.mapInResult()
}