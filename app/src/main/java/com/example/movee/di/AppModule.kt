package com.example.movee.di

import com.example.movee.data.remote.service.*
import com.example.movee.data.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun apiService(): ApiService {
        return ApiUtils.getData()
    }

    @Singleton
    @Provides
    fun provideAuthRepo() : AuthRepository{
        return AuthRepository()
    }

}