package fr.systemathicdev.feature.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.assisted.AssistedFactory
import fr.systemathicdev.commons.CustomResult
import fr.systemathicdev.core.products.adapter.input.detail.ViewDetailItem
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ProductDetailViewModelProviderFactory @Inject constructor(
    private val productDetailViewModelFactory: ProductDetailViewModelFactory
) : ViewModelProvider.Factory {
    @AssistedFactory
    interface ProductDetailViewModelFactory {
        fun create(
            product: MutableLiveData<CustomResult<List<ViewDetailItem>>> = MutableLiveData<CustomResult<List<ViewDetailItem>>>()
        ) : ProductDetailViewModel
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return productDetailViewModelFactory.create() as T
    }
}