package fr.systemathicdev.core.products.adapter.output

import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApi {
    @GET("records")
    suspend fun getProductsList() : RestProductList

    @GET("records/{id}")
    suspend fun getProductDetail(
        @Path("id") id: String
    ) : RestProductDetail
}