package com.satc.integrador_ai.di

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.GsonBuilder
import com.satc.integrador_ai.data.api.AuthInterceptor
import com.satc.integrador_ai.data.api.FormPreferenceService
import com.satc.integrador_ai.data.api.PlanoEstudoService
import com.satc.integrador_ai.data.model.request.LocalDateAdapter
import com.satc.integrador_ai.storage.UsuarioService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideBaseUrl(): String = "http://10.0.2.2:8080/"

    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String, okHttpClient: OkHttpClient): Retrofit {
        val gson = GsonBuilder()
            .registerTypeAdapter(LocalDate::class.java, LocalDateAdapter())
            .create()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideFormPreferenceService(retrofit: Retrofit): FormPreferenceService {
        return retrofit.create(FormPreferenceService::class.java)
    }

    @Provides
    @Singleton
    fun providePlanoEstudoService(retrofit: Retrofit): PlanoEstudoService {
        return retrofit.create(PlanoEstudoService::class.java)
    }

    @Provides
    @Singleton
    fun provideUsuarioService(retrofit: Retrofit): UsuarioService {
        return retrofit.create(UsuarioService::class.java)
    }
}