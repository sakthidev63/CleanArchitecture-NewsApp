package com.sakthi.cleanarchitectureapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sakthi.cleanarchitectureapp.BuildConfig
import com.sakthi.cleanarchitectureapp.core.AppDatabase
import com.sakthi.cleanarchitectureapp.feature_home.data.local.NewsDAO
import com.sakthi.cleanarchitectureapp.feature_home.data.remote.NewsApiService
import com.sakthi.cleanarchitectureapp.feature_home.data.repository.NewsRepositoryImpl
import com.sakthi.cleanarchitectureapp.feature_home.domain.repository.NewsRepository
import com.sakthi.cleanarchitectureapp.feature_home.domain.use_case.GetCombinedNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalDatabase(@ApplicationContext context: Context): AppDatabase {
       return Room.databaseBuilder(
            context = context,
            AppDatabase::class.java,
            "main.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideNewsDAO(database: AppDatabase): NewsDAO {
        return database.getNewsDAO()
    }

    @Provides
    @Singleton
    fun provideRemoteApi(client: OkHttpClient, converterFactory: Converter.Factory): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org")
            .client(client)
            .addConverterFactory(converterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideHTTPClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): Interceptor {
        return Interceptor { chain ->
            val originalRequest: Request = chain.request()

            val newRequest: Request = originalRequest.newBuilder()
                .addHeader(
                    "Authorization",
                    "Bearer ${BuildConfig.API_KEY}"
                )
                .build()

            chain.proceed(newRequest)
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun provideJsonConvertorFactory(): Converter.Factory {
        return Json {
            coerceInputValues = true
        }.asConverterFactory("application/json".toMediaType())
    }

    @Provides
    @Singleton
    fun provideAPIService(retrofit: Retrofit): NewsApiService {
        return retrofit.create(NewsApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(newsDAO: NewsDAO, newsApiService: NewsApiService): NewsRepository {
        return NewsRepositoryImpl(newsDAO, newsApiService)
    }

    @Provides
    @Singleton
    fun provideCombinedNewsUseCases(newsRepository: NewsRepository): GetCombinedNewsUseCase {
        return GetCombinedNewsUseCase(newsRepository)
    }

}