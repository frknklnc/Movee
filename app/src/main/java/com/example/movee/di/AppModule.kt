package com.example.movee.di

import android.content.Context
import com.example.movee.data.local.SharedPrefHelper
import com.example.movee.data.remote.service.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideMovieService(retrofit: Retrofit): MovieService {
        return retrofit.create(MovieService::class.java)
    }

    @Singleton
    @Provides
    fun provideTvService(retrofit: Retrofit): TvService {
        return retrofit.create(TvService::class.java)
    }

    @Singleton
    @Provides
    fun provideCastService(retrofit: Retrofit): CastService {
        return retrofit.create(CastService::class.java)
    }

    @Singleton
    @Provides
    fun provideAccountService(retrofit: Retrofit): AccountService {
        return retrofit.create(AccountService::class.java)
    }

    @Singleton
    @Provides
    fun provideSearchService(retrofit: Retrofit): SearchService {
        return retrofit.create(SearchService::class.java)
    }

    @Singleton
    @Provides
    fun provideSharedPrefHelper(@ApplicationContext context: Context): SharedPrefHelper {
        return SharedPrefHelper(context)
    }


}