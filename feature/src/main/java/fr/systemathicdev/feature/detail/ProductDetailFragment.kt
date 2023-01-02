package fr.systemathicdev.feature.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import fr.systemathicdev.commons.CustomResult
import fr.systemathicdev.commons.customviews.fragments.BackFragment
import fr.systemathicdev.commons.extensions.onError
import fr.systemathicdev.commons.extensions.onLoading
import fr.systemathicdev.commons.extensions.onSuccess
import androidx.lifecycle.Observer
import fr.systemathicdev.commons.extensions.setVisibility
import fr.systemathicdev.core.products.adapter.input.detail.ViewDetailItem
import fr.systemathicdev.feature.FeatureSubComponentProvider
import fr.systemathicdev.feature.databinding.FragmentProductDetailBinding
import fr.systemathicdev.feature.detail.adapter.ProductDetailAdapter
import fr.systemathicdev.feature.detail.adapter.ProductDetailLinkViewHolder
import timber.log.Timber
import javax.inject.Inject

class ProductDetailFragment :
    BackFragment(),
    ProductDetailLinkViewHolder.OnLinkButtonClickListener {

    @Inject
    lateinit var productsDetailViewModelProviderFactory: ProductDetailViewModelProviderFactory

    private val viewModel: ProductDetailViewModel by activityViewModels {
        productsDetailViewModelProviderFactory
    }

    private val productDetailAdapter: ProductDetailAdapter by lazy {
        ProductDetailAdapter(this)
    }

    private lateinit var binding: FragmentProductDetailBinding

    private val args: ProductDetailFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        (requireActivity().application as FeatureSubComponentProvider)
            .provideFeatureSubComponent()
            .inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentProductDetailBinding
        .inflate(inflater, container, false)
        .also { binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.list.apply {
            adapter = productDetailAdapter
            layoutManager = LinearLayoutManager(context)
        }

        viewModel.product.observe(viewLifecycleOwner,onProduct)
        viewModel.getProduct(args.id)
    }

    private val onProduct = Observer<CustomResult<List<ViewDetailItem>>>{ result ->
        result.onSuccess {
            hideLoader()
            productDetailAdapter.submitList(it)
        }
            .onError { handleError((result as CustomResult.Error).throwable)}
            .onLoading{ showLoader() }
    }

    override fun onLinkButtonClick(url: String) {
        viewModel.openPdfUrl(binding.root.context,url)
    }

    private fun handleError(t: Throwable){
        hideLoader()
        Toast.makeText(binding.root.context, "Une erreur est survenue : $t", Toast.LENGTH_SHORT).show()
        Timber.e(t)
    }

    private fun showLoader(){
        binding.progress.setVisibility(true)
    }

    private fun hideLoader(){
        binding.progress.setVisibility(false)
    }
}