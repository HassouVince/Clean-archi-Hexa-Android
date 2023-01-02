package fr.systemathicdev.core.products.adapter.input.productslist

data class ViewProductItem(
    val id: String,
    val name: String,
    val model: String,
    val date: String,
    val image: String,
) : ViewProductsListItem