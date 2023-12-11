package com.example.ecommerceapp.data

import com.example.ecommerceapp.data.ProductEntity
import retrofit2.Response
import retrofit2.http.GET

interface ProductService {

    @GET("products")
    suspend fun getProductList(): Response<List<ProductEntity>>
}