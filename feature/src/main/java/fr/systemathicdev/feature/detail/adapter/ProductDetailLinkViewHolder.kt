package fr.systemathicdev.feature.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.systemathicdev.commons.bindingadapters.setImageUrl
import fr.systemathicdev.core.products.adapter.input.detail.ViewDetailLinkItem
import fr.systemathicdev.core.products.adapter.input.productslist.ViewProductItem
import fr.systemathicdev.feature.databinding.ItemDetailLinkBinding
import fr.systemathicdev.feature.databinding.ItemProductBinding

class ProductDetailLinkViewHolder (
    private val binding: ItemDetailLinkBinding
) : BaseDetailViewHolder(binding.root) {

    interface OnLinkButtonClickListener{
        fun onLinkButtonClick(url: String)
    }

    constructor(parent: ViewGroup) : this(
        ItemDetailLinkBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        )
    )

    fun bind(
        item: ViewDetailLinkItem,
        onLinkButtonClickListener: OnLinkButtonClickListener
    ){
        binding.btnLink.setOnClickListener {
            onLinkButtonClickListener.onLinkButtonClick(item.link)
        }
    }
}