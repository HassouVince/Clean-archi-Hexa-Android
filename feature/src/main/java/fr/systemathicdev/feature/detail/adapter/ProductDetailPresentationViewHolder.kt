package fr.systemathicdev.feature.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.systemathicdev.commons.bindingadapters.setImageUrl
import fr.systemathicdev.core.products.adapter.input.detail.ViewDetailPresentationItem
import fr.systemathicdev.core.products.adapter.input.productslist.ViewProductItem
import fr.systemathicdev.feature.databinding.ItemDetailPresentationBinding
import fr.systemathicdev.feature.databinding.ItemProductBinding

class ProductDetailPresentationViewHolder (
    private val binding: ItemDetailPresentationBinding
) : BaseDetailViewHolder(binding.root) {

    constructor(parent: ViewGroup) : this(
        ItemDetailPresentationBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        )
    )

    fun bind(
        item: ViewDetailPresentationItem
    ){
        binding.apply {
            productImage.setImageUrl(item.image)
            productName.text = item.name
            productModel.text = item.model
        }
    }
}