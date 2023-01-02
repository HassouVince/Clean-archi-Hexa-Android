package fr.systemathicdev.feature.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.systemathicdev.commons.bindingadapters.setImageUrl
import fr.systemathicdev.core.products.adapter.input.detail.ViewDetailWarningItem
import fr.systemathicdev.core.products.adapter.input.productslist.ViewProductItem
import fr.systemathicdev.feature.databinding.ItemDetailWarningBinding
import fr.systemathicdev.feature.databinding.ItemProductBinding

class ProductDetailWarningViewHolder (
    private val binding: ItemDetailWarningBinding
) : BaseDetailViewHolder(binding.root) {

    constructor(parent: ViewGroup) : this(
        ItemDetailWarningBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        )
    )

    fun bind(
        item: ViewDetailWarningItem,
    ){
        binding.apply {
            reasonContent.text = item.reason
            warningContent.text = item.warning
        }
    }
}