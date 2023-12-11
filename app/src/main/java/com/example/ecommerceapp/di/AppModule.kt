package com.example.ecommerceapp.di

import com.example.ecommerceapp.data.ApiClient
import com.example.ecommerceapp.data.ProductRepository
import com.example.ecommerceapp.data.ProductRepositoryAPI
import com.example.ecommerceapp.data.ProductService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun providesProductService(): ProductService = ApiClient.getService()

    @Provides
    fun providesProductRepositoryAPI(service: ProductService)
    : ProductRepositoryAPI = ProductRepositoryAPI(service)

    @Provides
    fun providesProductRepository(productRepositoryAPI: ProductRepositoryAPI)
    : ProductRepository = productRepositoryAPI
}