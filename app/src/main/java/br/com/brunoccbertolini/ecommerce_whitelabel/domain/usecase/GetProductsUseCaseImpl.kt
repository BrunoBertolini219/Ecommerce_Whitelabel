package br.com.brunoccbertolini.ecommerce_whitelabel.domain.usecase

import br.com.brunoccbertolini.ecommerce_whitelabel.data.ProductRepository
import br.com.brunoccbertolini.ecommerce_whitelabel.domain.model.Product

class GetProductsUseCaseImpl(
    private val productRepository: ProductRepository
): GetProductsUseCase {
    override suspend fun invoke(): List<Product> {
        return productRepository.getProducts()
    }
}