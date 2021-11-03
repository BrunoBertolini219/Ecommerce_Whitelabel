package br.com.brunoccbertolini.ecommerce_whitelabel.domain.usecase

import android.net.Uri
import br.com.brunoccbertolini.ecommerce_whitelabel.data.ProductRepository

class UploadProductImageUseCaseImpl(
    private val productRepository: ProductRepository
) : UploadProductImageUseCase{

    override suspend fun invoke(imageUri: Uri): String {
        return productRepository.uploadProductImage(imageUri)
    }
}