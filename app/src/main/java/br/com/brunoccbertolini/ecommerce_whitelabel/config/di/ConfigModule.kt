package br.com.brunoccbertolini.ecommerce_whitelabel.config.di

import br.com.brunoccbertolini.ecommerce_whitelabel.config.Config
import br.com.brunoccbertolini.ecommerce_whitelabel.config.ConfigImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface ConfigModule {

    @Binds
    fun bindConfig(config: ConfigImpl): Config

}