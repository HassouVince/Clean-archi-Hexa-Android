package fr.systemathicdev.core.products.port.output

import fr.systemathicdev.core.products.domain.ProductDetail

interface GetProductDetailPort {
    suspend fun getProductDetail(id: String): ProductDetail
}