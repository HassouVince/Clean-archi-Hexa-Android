package fr.systemathicdev.feature

import dagger.Subcomponent
import fr.systemathicdev.feature.detail.ProductDetailFragment
import fr.systemathicdev.feature.products.ProductsListFragment

@Subcomponent
interface FeatureSubComponent {

    fun inject(productsListFragment: ProductsListFragment)
    fun inject(productDetailFragment: ProductDetailFragment)

    @Subcomponent.Builder
    interface Builder {
        fun build() : FeatureSubComponent
    }
}