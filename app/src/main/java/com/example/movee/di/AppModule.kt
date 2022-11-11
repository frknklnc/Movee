package com.example.movee.di

import com.example.movee.data.remote.api.ApiUtils
import com.example.movee.data.remote.api.MoviesAPI
import com.example.movee.data.remote.api.TvSeriesAPI
import com.example.movee.data.repository.MoviesRepository
import com.example.movee.data.repository.TvSeriesRepository
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
    fun provideMoviesAPI(): MoviesAPI {
        return ApiUtils.getMovies()
    }

    @Singleton
    @Provides
    fun provideMoviesRepo(api: MoviesAPI): MoviesRepository {
        return MoviesRepository(api)
    }

    @Singleton
    @Provides
    fun provideTvSeriesAPI(): TvSeriesAPI {
        return ApiUtils.getTvSeries()
    }

    @Singleton
    @Provides
    fun provideTvSeriesRepo(api: TvSeriesAPI): TvSeriesRepository {
        return TvSeriesRepository(api)
    }

}