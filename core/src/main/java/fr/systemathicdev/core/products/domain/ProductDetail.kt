package fr.systemathicdev.core.products.domain

data class ProductDetail(
    val name: String,
    val model: String,
    val date: String,
    val image: String,
    val category: String,
    val juridical: String,
    val identification: String,
    val geoZone: String,
    val reason: String,
    val warning: String,
    val recommendation: String,
    val pdfLink: String,
)