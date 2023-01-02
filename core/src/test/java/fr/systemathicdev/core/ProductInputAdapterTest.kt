package fr.systemathicdev.core

import fr.systemathicdev.commons.InputAdapterScope
import fr.systemathicdev.commons.extensions.getLastSuccessDataOrNull
import fr.systemathicdev.commons.extensions.mapInResult
import fr.systemathicdev.core.products.adapter.input.ProductInputAdapter
import fr.systemathicdev.core.products.adapter.input.detail.*
import fr.systemathicdev.core.products.adapter.input.productslist.ViewProductItem
import fr.systemathicdev.core.products.domain.Product
import fr.systemathicdev.core.products.domain.ProductDetail
import fr.systemathicdev.core.products.port.input.GetProductDetailUseCase
import fr.systemathicdev.core.products.port.input.GetProductsListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyString
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ProductInputAdapterTest {

    @Mock
    lateinit var getProductsListUseCase: GetProductsListUseCase

    @Mock
    lateinit var getProductDetailUseCase: GetProductDetailUseCase

    lateinit var adapter: ProductInputAdapter

    @Before
    fun initTests(){
        adapter = ProductInputAdapter(
            getProductsListUseCase,
            getProductDetailUseCase,
            InputAdapterScope(Dispatchers.Default)
        )
    }

    @Test
    fun `should return product list`() = runBlocking<Unit>{
        val productListFlow = flowOf(
            listOf(
                Product(
                    id = "ID",
                    name = "Name",
                    model = "Model",
                    image = "Image",
                    date = "Date",
                ),
                Product(
                    id = "ID2",
                    name = "Name2",
                    model = "Model2",
                    image = "Image2",
                    date = "Date2",
                )
            )
        ).mapInResult()

        `when`(getProductsListUseCase.getProductsList()).thenReturn(productListFlow)

        assertThat(adapter.getProductsList().getLastSuccessDataOrNull()).isEqualTo(
            listOf(
                ViewProductItem(
                    id = "ID",
                    name = "Name",
                    model = "Model",
                    image = "Image",
                    date = "Date",
                ),
                ViewProductItem(
                    id = "ID2",
                    name = "Name2",
                    model = "Model2",
                    image = "Image2",
                    date = "Date2",
                ),
            )
        )
    }

    @Test
    fun `should return product detail`() = runBlocking<Unit>{
        val productFlow = flowOf(
            ProductDetail(
                name = "Name",
                identification = "Ref",
                juridical = "Juridical",
                category = "Category",
                model = "Model",
                reason = "Reason",
                geoZone = "GeoZone",
                warning = "Warning",
                recommendation = "Recommendation",
                image = "Image",
                pdfLink = "PdfLink",
                date = "Date"
            )
        ).mapInResult()

        `when`(getProductDetailUseCase.getProductDetail(anyString())).thenReturn(productFlow)

        assertThat(adapter.getProductDetail("ID").getLastSuccessDataOrNull()).isEqualTo(
            listOf(
                ViewDetailPresentationItem(
                    name = "Name",
                    model = "Model",
                    image = "Image"
                ),
                ViewDetailWarningItem(
                    reason = "Reason",
                    warning = "Warning"
                ),
                ViewDetailRecommendationItem(
                    recommendation = "Recommendation"
                ),
                ViewDetailInformationsItem(
                    value = "Category"
                ),
                ViewDetailInformationsItem(
                    value = "Ref"
                ),
                ViewDetailInformationsItem(
                    value = "Juridical"
                ),
                ViewDetailInformationsItem(
                    value = "GeoZone"
                ),
                ViewDetailInformationsItem(
                    value = "Date"
                ),
                ViewDetailLinkItem(
                    link = "PdfLink"
                )
            )
        )
    }
}