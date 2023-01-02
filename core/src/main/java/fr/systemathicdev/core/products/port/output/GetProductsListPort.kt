package fr.systemathicdev.core.products.port.output

import fr.systemathicdev.core.products.domain.Product

interface GetProductsListPort {
    suspend fun getProductsList(): List<Product>
}