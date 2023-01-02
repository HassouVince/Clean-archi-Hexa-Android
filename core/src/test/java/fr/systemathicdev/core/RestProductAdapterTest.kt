package fr.systemathicdev.core

import fr.systemathicdev.commons.OutputAdapterScopeMain
import fr.systemathicdev.commons.OutputAdapterScopeWorker
import fr.systemathicdev.core.products.adapter.output.*
import fr.systemathicdev.core.products.domain.Product
import fr.systemathicdev.core.products.domain.ProductDetail
import kotlinx.coroutines.Dispatchers
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
class RestProductAdapterTest {
    @Mock
    lateinit var  productApi: ProductApi

    private lateinit var productAdapter: RestProductAdapter

    @Before
    fun initTests(){
        productAdapter = RestProductAdapter(
            OutputAdapterScopeMain(Dispatchers.Default),
            OutputAdapterScopeWorker(Dispatchers.Default),
            productApi
        )
    }

    @Test
    fun `should return product list`() = runBlocking<Unit> {
        val restProductList = RestProductList(
            records = listOf(
                RestProductDetail(
                    links = emptyList(),
                    record = RestRecord(
                        id = "ID",
                        timestamp = "10-2022",
                        size = 1,
                        fields = RestField(
                            name = "Name",
                            ref = "Ref",
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
                    )
                ),
                RestProductDetail(
                    links = emptyList(),
                    record = RestRecord(
                        id = "ID2",
                        timestamp = "11-2022",
                        size = 1,
                        fields = RestField(
                            name = "Name2",
                            ref = "Ref2",
                            juridical = "Juridical2",
                            category = "Category2",
                            model = "Model2",
                            reason = "Reason2",
                            geoZone = "GeoZone2",
                            warning = "Warning2",
                            recommendation = "Recommendation2",
                            image = "Image2",
                            pdfLink = "PdfLink2",
                            date = "Date2"
                        )
                    )
                )
            )
        )

        `when`(productApi.getProductsList()).thenReturn(restProductList)

        assertThat(productAdapter.getProductsList()).isEqualTo(
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
        )
    }

    @Test
    fun `should return product detail`() = runBlocking<Unit> {
        val restProduct = RestProductDetail(
            links = emptyList(),
            record = RestRecord(
                id = "ID",
                timestamp = "10-2022",
                size = 1,
                fields = RestField(
                    name = "Name",
                    ref = "Ref",
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
            )
        )

        `when`(productApi.getProductDetail(anyString())).thenReturn(restProduct)

        assertThat(productAdapter.getProductDetail("ID")).isEqualTo(
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
        )
    }
}