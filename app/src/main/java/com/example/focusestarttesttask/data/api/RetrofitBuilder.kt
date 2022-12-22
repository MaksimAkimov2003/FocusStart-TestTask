package com.example.focusestarttesttask.data.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

fun provideLoggingInterceptor(): HttpLoggingInterceptor {
	val loggingInterceptor = HttpLoggingInterceptor()
	loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

	return loggingInterceptor
}

fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
	OkHttpClient.Builder()
		.connectTimeout(1000, TimeUnit.MILLISECONDS)
		.addInterceptor(loggingInterceptor)
		.build()

fun provideMoshi(): Moshi = Moshi.Builder()
	.add(KotlinJsonAdapterFactory())
	.build()

fun provideCardInfoService(okHttpClient: OkHttpClient, moshi: Moshi): CardInfoService =
	Retrofit.Builder()
		.run {
			baseUrl(BASE_URL)
			client(okHttpClient)
			addConverterFactory(MoshiConverterFactory.create(moshi))
			build()
		}.create(CardInfoService::class.java)

