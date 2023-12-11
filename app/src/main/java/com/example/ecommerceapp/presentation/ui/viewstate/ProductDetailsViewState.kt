package com.example.ecommerceapp.presentation.ui.viewstate

sealed class ProductDetailsViewState {
    data object Loading : ProductDetailsViewState()
    data object Success : ProductDetailsViewState()
    data class Error(val message: String) : ProductDetailsViewState()
}