package fr.systemathicdev.feature.products

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import fr.systemathicdev.commons.CustomResult
import fr.systemathicdev.commons.customviews.fragments.BackFragment
import fr.systemathicdev.core.products.adapter.input.productslist.ViewProductItem
import fr.systemathicdev.feature.FeatureSubComponentProvider
import fr.systemathicdev.feature.databinding.FragmentProductsListBinding
import fr.systemathicdev.feature.products.adapter.ProductViewHolder
import fr.systemathicdev.feature.products.adapter.ProductsListAdapter
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import fr.systemathicdev.commons.extensions.*
import timber.log.Timber
import javax.inject.Inject

class ProductsListFragment :
    BackFragment(),
    ProductViewHolder.OnProductClickListener {

    @Inject
    lateinit var productsListViewModelProviderFactory: ProductsListViewModelProviderFactory

    private val viewModel: ProductsListViewModel by activityViewModels {
        productsListViewModelProviderFactory
    }

    private val productsAdapter: ProductsListAdapter by lazy {
        ProductsListAdapter(this)
    }

    private lateinit var binding: FragmentProductsListBinding

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
    ) = FragmentProductsListBinding
        .inflate(inflater, container, false)
        .also { binding = it }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.productsList.apply {
            adapter = productsAdapter
            layoutManager = LinearLayoutManager(context)
        }

        viewModel.products.observe(viewLifecycleOwner,onNewProducts)
        viewModel.getProducts()
    }

    private val onNewProducts = Observer<CustomResult<List<ViewProductItem>>>{ result ->
        result.onSuccess {
            hideLoader()
            productsAdapter.submitList(it)
        }
            .onError { handleError((result as CustomResult.Error).throwable) }
            .onLoading{ showLoader() }
    }

    override fun onProductClickListener(id: String) {
        goToDetails(id)
    }

    private fun goToDetails(id: String){
        ProductsListFragmentDirections.actionProductsFragmentToProductDetailFragment(id)
            .let {
                findNavController().navigateSafe(it)
            }
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