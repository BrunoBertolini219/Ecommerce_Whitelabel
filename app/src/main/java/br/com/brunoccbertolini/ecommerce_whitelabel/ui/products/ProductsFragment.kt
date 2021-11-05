package br.com.brunoccbertolini.ecommerce_whitelabel.ui.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.fragment.findNavController
import br.com.brunoccbertolini.ecommerce_whitelabel.R
import br.com.brunoccbertolini.ecommerce_whitelabel.databinding.FragmentProductsBinding
import br.com.brunoccbertolini.ecommerce_whitelabel.domain.model.Product
import br.com.brunoccbertolini.ecommerce_whitelabel.util.PRODUCT_KEY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsFragment : Fragment() {
    private var _binding: FragmentProductsBinding? = null
    private val binding get() = _binding!!
    private val productsAdapter = ProductsAdapter()

    private val viewModel: ProductsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        obseveEvents()
        setListeners()
        observeNavBackStackEntry()
        getProducts()
    }

    private fun setRecyclerView() {
        binding.recyclerProducts.apply {
            setHasFixedSize(true)
            adapter = productsAdapter
        }
    }

    private fun setListeners() {
        binding.apply {

            swipeProducts.setOnRefreshListener {
                getProducts()
            }

            fabAdd.setOnClickListener {
                findNavController().navigate(R.id.action_productsFragment_to_addProductFragment)
            }

        }
    }

    private fun getProducts() {
        viewModel.getProducts()
    }

    private fun observeNavBackStackEntry() {
        findNavController().apply {
            val navBackStackEntry = getBackStackEntry(R.id.productsFragment)
            val savedStateHandle = navBackStackEntry.savedStateHandle
            val observer = LifecycleEventObserver { _, event ->
                if (event == Lifecycle.Event.ON_RESUME && savedStateHandle.contains(PRODUCT_KEY)) {
                    val product = savedStateHandle.get<Product>(PRODUCT_KEY)
                    val ondList = productsAdapter.differ.currentList
                    val newList = ondList.toMutableList().apply {
                        add(product)
                    }
                    productsAdapter.differ.submitList(newList)
                    binding.recyclerProducts.smoothScrollToPosition(newList.size - 1)
                    savedStateHandle.remove<Product>(PRODUCT_KEY)
                }
            }

            navBackStackEntry.lifecycle.addObserver(observer)

            viewLifecycleOwner.lifecycle.addObserver(LifecycleEventObserver { _, event ->
                if (event == Lifecycle.Event.ON_DESTROY) {
                    navBackStackEntry.lifecycle.removeObserver(observer)
                }
            })
        }
    }

    private fun obseveEvents() {
        viewModel.productsData.observe(viewLifecycleOwner) { products ->
            binding.swipeProducts.isRefreshing = false
            productsAdapter.differ.submitList(products)
        }

        viewModel.addButtonVisibilityData.observe(viewLifecycleOwner) { visibility ->
            binding.fabAdd.visibility = visibility
        }
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}