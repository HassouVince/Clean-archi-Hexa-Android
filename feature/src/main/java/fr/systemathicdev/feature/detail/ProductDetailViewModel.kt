package fr.systemathicdev.feature.detail

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import fr.systemathicdev.commons.CustomResult
import fr.systemathicdev.commons.SafeViewModel
import fr.systemathicdev.core.products.adapter.input.ProductInputAdapter
import fr.systemathicdev.core.products.adapter.input.detail.ViewDetailItem
import fr.systemathicdev.intentmanager.adapter.input.IntentAdapter
import kotlinx.coroutines.flow.collect

class ProductDetailViewModel @AssistedInject constructor(
    private val productInputAdapter: ProductInputAdapter,
    private val intentAdapter: IntentAdapter,
    @Assisted
    private val _product: MutableLiveData<CustomResult<List<ViewDetailItem>>> = MutableLiveData<CustomResult<List<ViewDetailItem>>>()
) : SafeViewModel() {

    val product: LiveData<CustomResult<List<ViewDetailItem>>>
        get() = _product

    fun getProduct(id: String) =
        safeLaunch {
            productInputAdapter
                .getProductDetail(id)
                .collect (_product::postValue)
        }

    fun openPdfUrl(context: Context, url: String) = safeLaunch {
        intentAdapter.openUrl(context,url)
    }
}