package fr.systemathicdev.feature.products

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.assisted.AssistedFactory
import fr.systemathicdev.commons.CustomResult
import fr.systemathicdev.core.products.adapter.input.productslist.ViewProductItem
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ProductsListViewModelProviderFactory @Inject constructor(
    private val productsListViewModelFactory: ProductsListViewModelFactory
) : ViewModelProvider.Factory {
    @AssistedFactory
    interface ProductsListViewModelFactory {
        fun create(
            products: MutableLiveData<CustomResult<List<ViewProductItem>>> = MutableLiveData<CustomResult<List<ViewProductItem>>>()
        ) : ProductsListViewModel
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return productsListViewModelFactory.create() as T
    }
}