package com.example.movieslist_mvp.ui.movieslist

import com.example.data.networking.response.main.movie.MovieResponse
import com.example.data.networking.response.similarmovies.SimilarMoviesResponse

interface MoviesListContract {
    interface View{
        fun showLoading(success: Boolean)
        fun showError(error: String)
    }

    interface Presenter{
        suspend fun getMainMovie(): MovieResponse?
        suspend fun getSimilarMovies(): SimilarMoviesResponse?
    }
}