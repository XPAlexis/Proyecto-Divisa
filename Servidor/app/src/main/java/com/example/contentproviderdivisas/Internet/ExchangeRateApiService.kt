package com.example.contentproviderdivisas.Internet

import com.example.contentproviderdivisas.Model.Posts
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://open.er-api.com"


private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()


private val retrofit =
    Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(BASE_URL)
        .build()

interface ExchangeRateApiService {
    @GET("v6/latest/USD")
    suspend fun getMonedas(): Moneda
    val posts: Call<Posts>
}

object ExchangeApi {
    val retrofitService: ExchangeRateApiService by lazy { retrofit.create(ExchangeRateApiService::class.java) }
}