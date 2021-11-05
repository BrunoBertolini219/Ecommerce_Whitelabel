package br.com.brunoccbertolini.ecommerce_whitelabel.ui.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import br.com.brunoccbertolini.ecommerce_whitelabel.databinding.FragmentProductsBinding
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsFragment: Fragment() {
    private var _binding: FragmentProductsBinding? = null
    private val binding get() = _binding!!
    private val productsAdapter = ProductsAdapter()

    private val viewModel: ProductsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        obseveEvents()
        viewModel.getProducts()
    }

    private fun setRecyclerView() {
        binding.recyclerProducts.apply {
            setHasFixedSize(true)
            adapter = productsAdapter
        }
    }

    private fun obseveEvents(){
        viewModel.productsData.observe(viewLifecycleOwner,){ products ->
            productsAdapter.differ.submitList(products)
        }

        viewModel.addButtonVisibilityData.observe(viewLifecycleOwner){ visibility ->
            binding.fabAdd.visibility = visibility
        }
    }



    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}