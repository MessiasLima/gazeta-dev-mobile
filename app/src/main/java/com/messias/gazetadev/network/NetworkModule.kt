package com.messias.gazetadev.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun provideGazetaDevApi(): GazetaDevApi{
        return Retrofit.Builder()
            .baseUrl(GAZETA_DEV_API_HOST)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GazetaDevApi::class.java)
    }

    companion object {
        private const val GAZETA_DEV_API_HOST = "https://gazeta.dev"
    }
}