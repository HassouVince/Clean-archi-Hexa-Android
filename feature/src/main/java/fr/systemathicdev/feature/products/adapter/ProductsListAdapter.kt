package fr.systemathicdev.feature.products.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.systemathicdev.core.products.adapter.input.productslist.ViewProductItem

class ProductsListAdapter(
    var onProductClickListener: ProductViewHolder.OnProductClickListener
) : RecyclerView.Adapter<ProductViewHolder>() {

    private var currentList: MutableList<ViewProductItem> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<ViewProductItem>){
        currentList.clear()
        currentList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProductViewHolder(parent)

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(currentList[position],onProductClickListener)
    }

    override fun getItemCount() = currentList.size
}