package com.example.ecommerceapp.presentation.ui.viewstate

import com.example.ecommerceapp.business.Product

sealed class ProductListViewState {
    data object Loading : ProductListViewState()
    data class Success(val products: ArrayList<Product>) : ProductListViewState()
    data class Error(val message: String) : ProductListViewState()
}