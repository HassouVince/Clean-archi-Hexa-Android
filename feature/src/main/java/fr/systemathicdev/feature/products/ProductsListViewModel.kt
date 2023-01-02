package fr.systemathicdev.feature.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import fr.systemathicdev.commons.CustomResult
import fr.systemathicdev.commons.SafeViewModel
import fr.systemathicdev.core.products.adapter.input.ProductInputAdapter
import fr.systemathicdev.core.products.adapter.input.productslist.ViewProductItem
import kotlinx.coroutines.flow.collect

class ProductsListViewModel @AssistedInject constructor(
    private val productInputAdapter: ProductInputAdapter,

    @Assisted
    private val _products: MutableLiveData<CustomResult<List<ViewProductItem>>> = MutableLiveData<CustomResult<List<ViewProductItem>>>()
) : SafeViewModel() {

    val products: LiveData<CustomResult<List<ViewProductItem>>>
        get() = _products

    fun getProducts() =
        safeLaunch {
            productInputAdapter
                .getProductsList()
                .collect(_products::postValue)
        }
}