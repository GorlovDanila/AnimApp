package com.example.animapp.di

import com.example.animapp.BuildConfig
import com.example.animapp.data.anim.datasource.remote.AnimApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { GsonConverterFactory.create() }
    single(qualifier = named("base_url")) { BuildConfig.API_ENDPOINT }
    single { provideLoggingInterceptor() }
    single {
        provideHttpClient(get())
    }
    single {
        provideRetrofit(
            httpClient = get(),
            gsonFactory = get(),
            baseUrl = get(named("base_url"))
        )
    }
    single {
        provideAnimApi(
            retrofit = get()
        )
    }
}

private fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
    level = if (BuildConfig.DEBUG) {
        HttpLoggingInterceptor.Level.BODY
    } else {
        HttpLoggingInterceptor.Level.NONE
    }
}

private fun provideHttpClient(
    loggingInterceptor: HttpLoggingInterceptor,
): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(10L, TimeUnit.SECONDS)
        .build()

private fun provideRetrofit(
    httpClient: OkHttpClient,
    gsonFactory: GsonConverterFactory,
    baseUrl: String,
): Retrofit = Retrofit.Builder()
    .client(httpClient)
    .baseUrl(baseUrl)
    .addConverterFactory(gsonFactory)
    .build()

private fun provideAnimApi(
    retrofit: Retrofit
): AnimApi = retrofit.create(AnimApi::class.java)
