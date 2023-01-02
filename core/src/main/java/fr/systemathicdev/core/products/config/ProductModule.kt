package fr.systemathicdev.core.products.config

import dagger.Binds
import dagger.Module
import dagger.Provides
import fr.systemathicdev.commons.ApiProvider
import fr.systemathicdev.core.products.adapter.output.ProductApi
import fr.systemathicdev.core.products.adapter.output.RestProductAdapter
import fr.systemathicdev.core.products.port.ProductService
import fr.systemathicdev.core.products.port.input.GetProductDetailUseCase
import fr.systemathicdev.core.products.port.input.GetProductsListUseCase
import fr.systemathicdev.core.products.port.output.GetProductDetailPort
import fr.systemathicdev.core.products.port.output.GetProductsListPort
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
abstract class ProductModule {

    @Binds
    abstract fun provideGetProductsListUseCase(productService: ProductService) : GetProductsListUseCase

    @Binds
    abstract fun provideGetProductDetailUseCase(productService: ProductService) : GetProductDetailUseCase

    @Binds
    abstract fun provideGetProductsListPort(restProductAdapter: RestProductAdapter) : GetProductsListPort

    @Binds
    abstract fun provideGetProductDetailPort(restProductAdapter: RestProductAdapter) : GetProductDetailPort

    companion object{
        @Provides
        @Singleton
        @JvmStatic
        fun provideProductApi(apiProvider: ApiProvider<Retrofit>) : ProductApi{
            return apiProvider.client().create(ProductApi::class.java)
        }
    }
}