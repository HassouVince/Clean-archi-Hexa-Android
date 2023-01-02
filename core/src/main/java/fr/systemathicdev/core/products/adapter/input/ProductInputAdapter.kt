package fr.systemathicdev.core.products.adapter.input

import fr.systemathicdev.commons.InputAdapter
import fr.systemathicdev.commons.InputAdapterScope
import fr.systemathicdev.commons.CustomResult
import fr.systemathicdev.commons.extensions.mapFlatInResult
import fr.systemathicdev.commons.extensions.mapInResult
import fr.systemathicdev.core.products.adapter.input.detail.*
import fr.systemathicdev.core.products.adapter.input.productslist.ViewProductItem
import fr.systemathicdev.core.products.domain.ProductDetail
import fr.systemathicdev.core.products.port.input.GetProductDetailUseCase
import fr.systemathicdev.core.products.port.input.GetProductsListUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductInputAdapter @Inject constructor(
    private val getProductsListUseCase: GetProductsListUseCase,
    private val getProductDetailUseCase: GetProductDetailUseCase,
    override val adapterScope: InputAdapterScope,
) : InputAdapter {

    suspend fun getProductsList(): Flow<CustomResult<List<ViewProductItem>>> =
        withContext(adapterScope.coroutineContext) {
            getProductsListUseCase
                .getProductsList()
                .mapFlatInResult { list ->
                    flowOf(
                        list.map {
                            ViewProductItem(
                                id = it.id,
                                name = it.name,
                                model = it.model,
                                date = it.date,
                                image = it.image
                            )

                        }
                    ).mapInResult()
                }
        }

    suspend fun getProductDetail(id: String): Flow<CustomResult<List<ViewDetailItem>>> =
        withContext(adapterScope.coroutineContext) {
            getProductDetailUseCase
                .getProductDetail(id)
                .mapFlatInResult {
                    flowOf(
                        listOf(
                            buildProductDetailPresentationItem(it),
                            buildProductDetailWarningItem(it),
                            ViewDetailRecommendationItem(recommendation = it.recommendation),
                        ).plus(buildProductDetailInfoList(it))
                            .plus(listOf(ViewDetailLinkItem(link = it.pdfLink)))
                    ).mapInResult()
                }
        }

    private fun buildProductDetailPresentationItem(product: ProductDetail) =
        ViewDetailPresentationItem(
            name = product.name,
            model = product.model,
            image = product.image
        )

    private fun buildProductDetailWarningItem(product: ProductDetail) =
        ViewDetailWarningItem(
            reason = product.reason,
            warning = product.warning,
        )

    private fun buildProductDetailInfoList(product: ProductDetail) =
        listOf(
            ViewDetailInformationsItem(value = product.category),
            ViewDetailInformationsItem(value = product.identification),
            ViewDetailInformationsItem(value = product.juridical),
            ViewDetailInformationsItem(value = product.geoZone),
            ViewDetailInformationsItem(value = product.date),
        )
}