package fr.systemathicdev.feature.detail.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import fr.systemathicdev.core.products.adapter.input.detail.*
import java.lang.IllegalArgumentException

class ProductDetailAdapter(
    private val onLinkButtonClickListener: ProductDetailLinkViewHolder.OnLinkButtonClickListener
) : RecyclerView.Adapter<BaseDetailViewHolder>(){

    private val diffCallback = object : DiffUtil.ItemCallback<ViewDetailItem>(){
        override fun areItemsTheSame(oldItem: ViewDetailItem, newItem: ViewDetailItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ViewDetailItem, newItem: ViewDetailItem): Boolean {
            return oldItem == newItem
        }

    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(items: List<ViewDetailItem>){
        differ.submitList(items)
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: BaseDetailViewHolder, position: Int) {
        val item = differ.currentList[position]

        if(item is ViewDetailPresentationItem && holder is ProductDetailPresentationViewHolder){
            holder.bind(item)
        }else if(item is ViewDetailWarningItem && holder is ProductDetailWarningViewHolder){
            holder.bind(item)
        }else if(item is ViewDetailRecommendationItem && holder is ProductDetailRecommendationViewHolder){
            holder.bind(item)
        }else if(item is ViewDetailInformationsItem && holder is ProductDetailInfosViewHolder){
            holder.bind(item)
        }else if(item is ViewDetailLinkItem && holder is ProductDetailLinkViewHolder){
            holder.bind(item, onLinkButtonClickListener)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseDetailViewHolder {
        return when(viewType){
            PRESENTATION_VIEW_TYPE -> ProductDetailPresentationViewHolder(parent)
            WARNING_VIEW_TYPE -> ProductDetailWarningViewHolder(parent)
            RECOMMENDATION_VIEW_TYPE -> ProductDetailRecommendationViewHolder(parent)
            INFO_VIEW_TYPE -> ProductDetailInfosViewHolder(parent)
            LINK_VIEW_TYPE -> ProductDetailLinkViewHolder(parent)
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(differ.currentList[position]){
            is ViewDetailPresentationItem -> PRESENTATION_VIEW_TYPE
            is ViewDetailWarningItem -> WARNING_VIEW_TYPE
            is ViewDetailRecommendationItem -> RECOMMENDATION_VIEW_TYPE
            is ViewDetailInformationsItem -> INFO_VIEW_TYPE
            is ViewDetailLinkItem -> LINK_VIEW_TYPE
            else -> throw IllegalArgumentException()
        }
    }

    companion object{
        private const val PRESENTATION_VIEW_TYPE = 0
        private const val WARNING_VIEW_TYPE = 1
        private const val RECOMMENDATION_VIEW_TYPE = 2
        private const val INFO_VIEW_TYPE = 3
        private const val LINK_VIEW_TYPE = 4
    }
}