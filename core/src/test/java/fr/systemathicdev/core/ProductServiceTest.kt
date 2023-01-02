package fr.systemathicdev.core

import fr.systemathicdev.commons.extensions.getLastSuccessDataOrNull
import fr.systemathicdev.core.products.domain.Product
import fr.systemathicdev.core.products.domain.ProductDetail
import fr.systemathicdev.core.products.port.ProductService
import fr.systemathicdev.core.products.port.output.GetProductDetailPort
import fr.systemathicdev.core.products.port.output.GetProductsListPort
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
class ProductServiceTest {

    @Mock
    lateinit var getProductsListPort: GetProductsListPort

    @Mock
    lateinit var getProductDetailPort: GetProductDetailPort

    lateinit var service: ProductService

    @Before
    fun initTests(){
        service = ProductService(
            getProductsListPort,
            getProductDetailPort
        )
    }

    @Test
    fun `should return product list`() = runBlocking<Unit>{
        val products = listOf(
            Product(
                id = "ID",
                name = "Name",
                model = "Model",
                image = "Image",
                date = "10-2022",
            ),
            Product(
                id = "ID2",
                name = "Name2",
                model = "Model2",
                image = "Image2",
                date = "10-2022",
            )
        )

        `when`(getProductsListPort.getProductsList()).thenReturn(products)

        assertThat(service.getProductsList().getLastSuccessDataOrNull()).isEqualTo(products)
    }

    @Test
    fun `should return product detail`() = runBlocking<Unit>{
        val product = ProductDetail(
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

        `when`(getProductDetailPort.getProductDetail(anyString())).thenReturn(product)

        assertThat(service.getProductDetail("1").getLastSuccessDataOrNull()).isEqualTo(product)
    }
}