package com.skyline_info_system.baseapp.di

import android.content.Context
import android.net.ConnectivityManager
import com.skyline_info_system.baseapp.BuildConfig
import com.skyline_info_system.baseapp.network.ApiService
import com.skyline_info_system.baseapp.utils.Constants
import com.skyline_info_system.baseapp.utils.Constants.API_KEY
import com.skyline_info_system.baseapp.utils.Constants.ApiStatusCode.ACCESS_TOKEN
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideHeaderInterceptor(): Interceptor {

        return Interceptor { chain ->
            var request = chain.request()
            request = request.newBuilder().addHeader(ACCESS_TOKEN, "Bearer $API_KEY").build()
            val response = chain.proceed(request)
            response
        }
    }

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) {
                this.level = HttpLoggingInterceptor.Level.BODY
            }
        }
    }

    @Singleton
    @Provides
    fun provideOkHttp(httpLoggingInterceptor: HttpLoggingInterceptor, headerInterceptor: Interceptor): OkHttpClient {
        return if (BuildConfig.DEBUG) {
            OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).addInterceptor(headerInterceptor).build()
        } else {
            OkHttpClient.Builder().addInterceptor(headerInterceptor).build()
        }

    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient).build()
    }

    @Provides
    fun provideApiClient(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideConnectivityManager(@ApplicationContext context: Context): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
}