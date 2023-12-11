package com.example.ecommerceapp.data

import com.example.ecommerceapp.business.Product

interface ProductRepository {
    suspend fun getMockData(): List<Product>
    suspend fun getProducts(): List<Product>
}