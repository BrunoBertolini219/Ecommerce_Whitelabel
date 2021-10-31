package br.com.brunoccbertolini.ecommerce_whitelabel.data

import android.net.Uri
import br.com.brunoccbertolini.ecommerce_whitelabel.domain.model.Product

interface ProductDataSource {

    suspend fun getProducts(): List<Product>

    suspend fun uploadProductImage(imageUri: Uri): String

    suspend fun createProduct(product: Product): Product
}