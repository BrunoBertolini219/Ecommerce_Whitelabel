package br.com.brunoccbertolini.ecommerce_whitelabel.domain.usecase

import android.net.Uri
import br.com.brunoccbertolini.ecommerce_whitelabel.data.ProductRepository
import br.com.brunoccbertolini.ecommerce_whitelabel.domain.model.Product
import java.util.*

class CreateProductUseCaseImpl(
    private val uploadProductImageUseCase: UploadProductImageUseCase,
    private val productRepository: ProductRepository
) : CreateProductUseCase {

    override suspend fun invoke(descrition: String, price: Double, imageUri: Uri): Product {
        return try {
            val imageUrl = uploadProductImageUseCase(imageUri)
            val product = Product(UUID.randomUUID().toString(), descrition, price, imageUrl)
            productRepository.createProduct(product)
        } catch (e: Exception) {
            throw e
        }

    }
}