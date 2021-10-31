package br.com.brunoccbertolini.ecommerce_whitelabel.domain.usecase

import br.com.brunoccbertolini.ecommerce_whitelabel.domain.model.Product

interface GetProductsUseCase {

    suspend operator fun invoke(): List<Product>
}