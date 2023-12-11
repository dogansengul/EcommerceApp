package com.example.ecommerceapp.data

import com.example.ecommerceapp.business.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductRepositoryAPI @Inject constructor(private val service: ProductService): ProductRepository {

    override suspend fun getMockData(): List<Product> {
        return withContext(Dispatchers.IO) {
            delay(2000)
            (1..5).map {
                Product("1",
                    "PlayStation 1",
                    "PlayStation description",
                    "12",
                    "PlayStation 1",
                    true,
                    true) }
        }
    }

    override suspend fun getProducts(): List<Product> {
        return service.getProductList().body()!!.mapIndexed() { index, it ->
            Product(
                id = (index + 1).toString(), // index + 1 ile id'yi sırasıyla arttır
                title = it.title,
                description = it.description,
                price = "$ ${it.price}",
                imageUrl = it.imageUrl,
                // Diğer özellikler
                true,
                true
            )
        }
    }
}