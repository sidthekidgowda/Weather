package com.png.interview.dagger.module

import android.app.Application

import com.png.interview.api.common_model.NetworkResponseAdapterFactory
import com.png.interview.dagger.scope.ApplicationScope
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.Date
import javax.inject.Qualifier

@Module
class CommonApiModule {

    @Provides
    fun provideOkHttpClient(app: Application): OkHttpClient.Builder {
        val builder = OkHttpClient.Builder()
            .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        return builder
    }

    @Provides
    fun provideRetrofitBuilder(): Retrofit.Builder = Retrofit.Builder().addCallAdapterFactory(NetworkResponseAdapterFactory())

    @Provides
    @ApplicationScope
    fun provideMoshi(): Moshi.Builder =
        Moshi
            .Builder()
            .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
            .add(KotlinJsonAdapterFactory())

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class MoshiNoDateAdapter
}
