package com.example.movee.di

import android.content.Context
import com.example.movee.data.local.SharedPrefHelper
import com.example.movee.data.remote.service.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideSharedPrefHelper(@ApplicationContext context: Context): SharedPrefHelper {
        return SharedPrefHelper(context)
    }


}