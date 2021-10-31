package br.com.brunoccbertolini.ecommerce_whitelabel.domain.usecase

import android.net.Uri


interface UploadProductImageUseCase {

    suspend operator fun invoke(imageUri: Uri): String

}