package br.com.brunoccbertolini.ecommerce_whitelabel.domain.usecase

import android.net.Uri
import br.com.brunoccbertolini.ecommerce_whitelabel.domain.model.Product

interface CreateProductUseCase {

    suspend operator fun invoke(descrition: String, price: Double, imageUri: Uri): Product

}