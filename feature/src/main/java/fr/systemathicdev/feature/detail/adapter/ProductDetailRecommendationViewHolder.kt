package fr.systemathicdev.feature.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.systemathicdev.commons.bindingadapters.setImageUrl
import fr.systemathicdev.core.products.adapter.input.detail.ViewDetailRecommendationItem
import fr.systemathicdev.core.products.adapter.input.productslist.ViewProductItem
import fr.systemathicdev.feature.databinding.ItemDetailRecommendationBinding
import fr.systemathicdev.feature.databinding.ItemProductBinding

class ProductDetailRecommendationViewHolder (
    private val binding: ItemDetailRecommendationBinding
) : BaseDetailViewHolder(binding.root) {

    constructor(parent: ViewGroup) : this(
        ItemDetailRecommendationBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        )
    )

    fun bind(
        item: ViewDetailRecommendationItem,
    ){
        binding.recommendationContent.text = item.recommendation
    }
}