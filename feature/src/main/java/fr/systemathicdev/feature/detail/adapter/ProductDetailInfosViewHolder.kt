package fr.systemathicdev.feature.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.systemathicdev.core.products.adapter.input.detail.ViewDetailInformationsItem
import fr.systemathicdev.feature.databinding.ItemDetailInformationsBinding

class ProductDetailInfosViewHolder(
    private val binding: ItemDetailInformationsBinding
) : BaseDetailViewHolder(binding.root) {

    constructor(parent: ViewGroup) : this(
        ItemDetailInformationsBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        )
    )

    fun bind(
        item: ViewDetailInformationsItem,
    ){
        binding.apply {
            title.text = item.value
            content.text = item.value
        }
    }
}