package com.gigcreator.data.di

import com.gigcreator.data.network.TorrentRetrofit
import com.gigcreator.data.network.TorrentRetrofit.Companion.BASE_URL
import com.gigcreator.data.network.TorrentRetrofit.Companion.TIMEOUT
import com.gigcreator.data.repository.TorrentRetrofitRepositoryImpl
import com.gigcreator.domain.repository.TorrentRetrofitRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
        .callTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
        .readTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
        .writeTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideTorrentRetrofit(retrofit: Retrofit): TorrentRetrofit =
        retrofit.create(TorrentRetrofit::class.java)

    @Provides
    @Singleton
    fun provideTorrentRetrofitRepository(retrofit: TorrentRetrofit): TorrentRetrofitRepository =
        TorrentRetrofitRepositoryImpl(retrofit)

}