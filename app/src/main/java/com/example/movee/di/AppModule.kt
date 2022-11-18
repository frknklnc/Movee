package com.example.movee.di

import com.example.movee.data.remote.service.CastService
import com.example.movee.data.remote.service.ApiUtils
import com.example.movee.data.remote.service.MovieService
import com.example.movee.data.remote.service.TvService
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
    fun provideMoviesAPI(): MovieService {
        return ApiUtils.getMovies()
    }

    /*@Singleton
    @Provides
    fun provideMoviesRepo(api: MoviesAPI): MoviesRepository {
        return MoviesRepository(api)
    }*/

    @Singleton
    @Provides
    fun provideTvSeriesAPI(): TvService {
        return ApiUtils.getTvSeries()
    }

    /*@Singleton
    @Provides
    fun provideTvSeriesRepo(api: TvSeriesAPI): TvSeriesRepository {
        return TvSeriesRepository(api)
    }*/

    @Singleton
    @Provides
    fun provideActorService(): CastService{
        return ApiUtils.getActors()
    }

}