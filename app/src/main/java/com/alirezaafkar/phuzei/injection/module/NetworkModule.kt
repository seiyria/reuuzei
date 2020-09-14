package com.seiyria.reuuzei.injection.module

import com.seiyria.reuuzei.API_SCOPE
import com.seiyria.reuuzei.AUTHORIZATION
import com.seiyria.reuuzei.BASE_API_URL
import com.seiyria.reuuzei.BASE_URL
import com.seiyria.reuuzei.BuildConfig
import com.seiyria.reuuzei.CODE
import com.seiyria.reuuzei.KEY_CLIENT_ID
import com.seiyria.reuuzei.KEY_REDIRECT_URI
import com.seiyria.reuuzei.KEY_RESPONSE_TYPE
import com.seiyria.reuuzei.KEY_SCOPE
import com.seiyria.reuuzei.REDIRECT_URI
import com.seiyria.reuuzei.SCHEME
import com.seiyria.reuuzei.data.api.AlbumsApi
import com.seiyria.reuuzei.data.api.PhotosApi
import com.seiyria.reuuzei.data.api.TokenApi
import com.seiyria.reuuzei.data.pref.AppPreferences
import com.seiyria.reuuzei.data.pref.token
import com.seiyria.reuuzei.injection.qualifier.AuthorizationInterceptor
import com.seiyria.reuuzei.injection.qualifier.LoggingInterceptor
import com.seiyria.reuuzei.util.TokenAuthenticator
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Alireza Afkar on 16/3/2018AD.
 */
@Module(includes = [DataModule::class])
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonFactory: GsonConverterFactory,
        authenticator: TokenAuthenticator,
        rxJavaFactory: RxJava2CallAdapterFactory
    ): Retrofit {
        val client = okHttpClient.newBuilder()
            .authenticator(authenticator).build()

        return Retrofit.Builder()
            .baseUrl(BASE_API_URL)
            .client(client)
            .addConverterFactory(gsonFactory)
            .addCallAdapterFactory(rxJavaFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @LoggingInterceptor loggingInterceptor: Interceptor,
        @AuthorizationInterceptor authorizationInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authorizationInterceptor)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRxJavaFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create()
    }

    @Provides
    @Singleton
    fun provideGsonFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @Singleton
    @LoggingInterceptor
    fun provideLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    @AuthorizationInterceptor
    fun provideAuthorizationInterceptor(prefs: AppPreferences): Interceptor {
        return Interceptor {
            val original = it.request()
            val request = original.newBuilder()
                .header(AUTHORIZATION, prefs.token())
                .build()
            return@Interceptor it.proceed(request)
        }
    }

    @Provides
    fun provideAuthUrl(): HttpUrl {
        return HttpUrl.Builder()
            .scheme(SCHEME)
            .host(BASE_URL)
            .addPathSegments("o/oauth2/v2/auth")
            .addQueryParameter(KEY_SCOPE, API_SCOPE)
            .addQueryParameter(KEY_RESPONSE_TYPE, CODE)
            .addQueryParameter(KEY_REDIRECT_URI, REDIRECT_URI)
            .addQueryParameter(KEY_CLIENT_ID, BuildConfig.CLIENT_ID)
            .build()
    }

    @Provides
    fun provideTokenAuthenticator(
        gson: Gson,
        client: OkHttpClient,
        prefs: AppPreferences
    ): TokenAuthenticator {
        return TokenAuthenticator(gson, client, prefs)
    }

    @Provides
    @Singleton
    fun provideTokenApi(retrofit: Retrofit): TokenApi {
        return retrofit.create(TokenApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAlbumsApi(retrofit: Retrofit): AlbumsApi {
        return retrofit.create(AlbumsApi::class.java)
    }

    @Provides
    @Singleton
    fun providePhotosApi(retrofit: Retrofit): PhotosApi {
        return retrofit.create(PhotosApi::class.java)
    }
}
