package com.eazy.stcbusiness.network_module

import android.content.Context
import com.eazy.stcbusiness.BuildConfig
import com.eazy.stcbusiness.utils.AppLOGG
import com.google.gson.annotations.SerializedName
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.IOException
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ServerRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PreferenceRetrofit

@Module
@InstallIn(SingletonComponent::class)

class RetrofitGenerator {

    companion object {
        private const val API_READ_TIMEOUT: Long = 2
        private const val API_CONNECT_TIMEOUT: Long = 2
    }

    @Provides
    fun provideServiceApi(@ApplicationContext appContext: Context): ServiceApi {
        return provideRetrofit(appContext).create(ServiceApi::class.java)
    }

    @Provides
    @PreferenceRetrofit
    fun provideRetrofit(@ApplicationContext appContext: Context): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(provideAuthenticateOkHttpClient(appContext))
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(EnumRetrofitConverterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideAuthenticateOkHttpClient(@ApplicationContext appContext: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthenticationInterceptor(appContext))
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            })

            .readTimeout(API_READ_TIMEOUT, TimeUnit.MINUTES)
            .connectTimeout(API_CONNECT_TIMEOUT, TimeUnit.MINUTES)
            .writeTimeout(API_READ_TIMEOUT, TimeUnit.MINUTES)
            .build()
    }

    class AuthenticationInterceptor(@ApplicationContext appContext: Context): Interceptor {
        private val context = appContext
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val originalRequest = chain.request()
            val requestBuilder = originalRequest.newBuilder()

            val authToken = "Bearer " // + Utils.getString("token", context)
            AppLOGG.d("authToken", authToken)

            requestBuilder.addHeader("Accept", "application/json")
            requestBuilder.addHeader("Content-type", "application/json")
            requestBuilder.addHeader("Authorization", authToken)
            requestBuilder.addHeader("Request-Type", "mobile")
            requestBuilder.method(originalRequest.method, originalRequest.body)
            return chain.proceed(requestBuilder.build())
        }
    }

}

class EnumRetrofitConverterFactory : Converter.Factory() {
    override fun stringConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<Enum<*>, String>? =
        if (type is Class<*> && type.isEnum) {
            Converter { enum ->
                try {
                    enum.javaClass.getField(enum.name)
                        .getAnnotation(SerializedName::class.java)?.value
                } catch (exception: Exception) {
                    null
                } ?: enum.toString()
            }
        } else {
            null
        }
}