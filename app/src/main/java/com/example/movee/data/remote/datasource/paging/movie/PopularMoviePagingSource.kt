package com.example.movee.data.remote.datasource.paging.movie

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movee.data.repository.MovieRepository
import com.example.movee.uimodels.movie.PopularMovieUiModel
import com.example.movee.util.CustomUnsuccessfulResponseError
import retrofit2.HttpException

class PopularMoviePagingSource(private val repository: MovieRepository) :
    PagingSource<Int, PopularMovieUiModel>() {

    override fun getRefreshKey(state: PagingState<Int, PopularMovieUiModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PopularMovieUiModel> {
        try {
            val nextPageNumber = params.key ?: 1
            val response = repository.getPopularMoviesList(page = nextPageNumber)
            if (response.data != null) {
                return LoadResult.Page(
                    data = response.data.movies.map { it.toUiModel() },
                    prevKey = null,
                    nextKey = if (nextPageNumber + 1 <= response.data.totalPages) nextPageNumber + 1 else null
                )
            } else {
                return LoadResult.Error(CustomUnsuccessfulResponseError("Data is null"))
            }

        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}