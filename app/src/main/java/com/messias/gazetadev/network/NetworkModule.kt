package com.messias.gazetadev.network

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun provideCache(@ApplicationContext context: Context) =
        Cache(context.cacheDir, CACHE_SIZE.toLong())

    @Provides
    fun provideHttpClient(cache: Cache) = OkHttpClient.Builder().cache(cache).build()

    @Provides
    fun provideGazetaDevApi(okHttpClient: OkHttpClient): GazetaDevApi {
        return Retrofit.Builder()
            .baseUrl(GAZETA_DEV_API_HOST)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(GazetaDevApi::class.java)
    }

    companion object {
        private const val CACHE_SIZE = 10 * 1024 * 1024
        private const val GAZETA_DEV_API_HOST = "https://gazeta.dev"
    }
}
