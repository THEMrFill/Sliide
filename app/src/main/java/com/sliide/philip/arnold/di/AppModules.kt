package com.sliide.philip.arnold.di

import com.google.gson.GsonBuilder
import com.sliide.philip.arnold.BuildConfig
import com.sliide.philip.arnold.api.ApiNetwork
import com.sliide.philip.arnold.repo.UserRepositoryImpl
import com.sliide.philip.arnold.repo.UserRepository
import com.sliide.philip.arnold.ui.main.MainViewModel
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val appModules = module {
    // The Retrofit service using our custom HTTP client instance as a singleton
    single {
        createWebService(
            okHttpClient = createHttpClient(),
            baseUrl = BuildConfig.API_URL,
        )
    }

    single<UserRepository> { UserRepositoryImpl(get()) }

    viewModel { MainViewModel(get()) }

}

/* Returns a custom OkHttpClient instance with interceptor. Used for building Retrofit service */
fun createHttpClient(): OkHttpClient {
    val client = OkHttpClient.Builder()
    client.readTimeout(30, TimeUnit.SECONDS)
    return client.addInterceptor {
        val original = it.request()
        val requestBuilder = original.newBuilder()
        requestBuilder.header("Content-Type", "application/json")
        val request = requestBuilder.method(original.method, original.body).build()
        return@addInterceptor it.proceed(request)
    }.build()
}

/* function to build our Retrofit service */
fun createWebService(
    okHttpClient: OkHttpClient,
    baseUrl: String
): ApiNetwork {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .build()
    return retrofit.create(ApiNetwork::class.java)
}