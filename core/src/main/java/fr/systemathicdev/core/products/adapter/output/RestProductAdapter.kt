package fr.systemathicdev.core.products.adapter.output

import fr.systemathicdev.commons.OutputAdapter
import fr.systemathicdev.commons.OutputAdapterScopeMain
import fr.systemathicdev.commons.OutputAdapterScopeWorker
import fr.systemathicdev.core.products.domain.Product
import fr.systemathicdev.core.products.domain.ProductDetail
import fr.systemathicdev.core.products.port.output.GetProductDetailPort
import fr.systemathicdev.core.products.port.output.GetProductsListPort
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RestProductAdapter @Inject constructor(
    override val outputAdapterScopeMain: OutputAdapterScopeMain,
    override val outputAdapterScopeWorker: OutputAdapterScopeWorker,
    private val productApi: ProductApi
) : OutputAdapter,
    GetProductsListPort,
    GetProductDetailPort {

    override suspend fun getProductsList(): List<Product> =
        withContext(outputAdapterScopeWorker.coroutineContext){
            productApi
                .getProductsList()
                .toProducts()
        }


    override suspend fun getProductDetail(id: String): ProductDetail =
        withContext(outputAdapterScopeWorker.coroutineContext){
            productApi
                .getProductDetail(id)
                .toProductDetail()
        }
}