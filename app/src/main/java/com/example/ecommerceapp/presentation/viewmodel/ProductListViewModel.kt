package com.example.ecommerceapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.data.ProductRepository
import com.example.ecommerceapp.business.Product
import com.example.ecommerceapp.presentation.ui.viewstate.ProductListViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(private val productRepository: ProductRepository): ViewModel() {
    private val _productListViewState = MutableLiveData<ProductListViewState>()
    val productListViewState: LiveData<ProductListViewState>
        get() = _productListViewState

    fun loadProductList() {
        _productListViewState.value = ProductListViewState.Loading
        viewModelScope.launch {
            _productListViewState.value = ProductListViewState.Success(returnProducts())
        }
    }

    private suspend fun returnProducts() : ArrayList<Product> {
        return ArrayList(productRepository.getProducts())
    }

    private suspend fun returnMockData() : ArrayList<Product> {
        return ArrayList(productRepository.getMockData())
    }
}