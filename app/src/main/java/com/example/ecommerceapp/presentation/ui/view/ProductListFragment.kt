package com.example.ecommerceapp.presentation.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ecommerceapp.R
import com.example.ecommerceapp.databinding.FragmentProductListBinding
import com.example.ecommerceapp.presentation.ui.viewstate.ProductListViewState
import com.example.ecommerceapp.presentation.ui.adapter.ProductCardListAdapter
import com.example.ecommerceapp.presentation.ui.adapter.RecyclerViewItemDecoration
import com.example.ecommerceapp.business.Product
import com.example.ecommerceapp.presentation.viewmodel.ProductListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListFragment : Fragment() {

    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!
    private lateinit var productListAdapter: ProductCardListAdapter
    private var products: ArrayList<Product>? = null
    private val productListViewModel: ProductListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentProductListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRV()
        productListViewModel.productListViewState.observe(viewLifecycleOwner) { productListViewState ->
            updateUI(productListViewState)
        }
        productListViewModel.loadProductList()

    }

    private fun updateUI(viewState: ProductListViewState) {
        when (viewState) {
            is ProductListViewState.Error -> {
                Toast.makeText(requireContext(), viewState.message, Toast.LENGTH_SHORT).show()
                binding.rvProducts.visibility = View.GONE
            }

            ProductListViewState.Loading -> {
                binding.rvProducts.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
            }

            is ProductListViewState.Success -> {
                binding.rvProducts.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
                productListAdapter.submitData(viewState.products)
                products = viewState.products
            }
        }
    }

    private fun setUpRV() {
        productListAdapter = ProductCardListAdapter(){
            val bundle = Bundle()
            bundle.putParcelable("product", products?.get(it))
            Navigation.findNavController(binding.root).navigate(R.id.action_productListFragment_to_productDetailsFragment, bundle)
        }
        binding.rvProducts.apply {
            adapter = productListAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
            addItemDecoration(RecyclerViewItemDecoration(20))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}