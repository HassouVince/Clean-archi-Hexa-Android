package fr.systemathicdev.feature.products.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.systemathicdev.commons.bindingadapters.setImageUrl
import fr.systemathicdev.core.products.adapter.input.productslist.ViewProductItem
import fr.systemathicdev.feature.databinding.ItemProductBinding

class ProductViewHolder(
    private val binding: ItemProductBinding
) : RecyclerView.ViewHolder(binding.root) {

    interface OnProductClickListener{
        fun onProductClickListener(id: String)
    }

    constructor(parent: ViewGroup) : this(
        ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        )
    )

    fun bind(
        item: ViewProductItem,
        onProductClickListener: OnProductClickListener
    ){
        binding.apply {
            imageProduct.setImageUrl(item.image)
            productName.text = item.name
            productRef.text = item.model
            date.text = item.date

            root.setOnClickListener {
                onProductClickListener.onProductClickListener(item.id)
            }
        }
    }
}