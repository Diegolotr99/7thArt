package com.diegogutierrez.data.source

import com.diegogutierrez.domain.Movie

interface RemoteDataSource {
    suspend fun getPopularMovies(apiKey: String, region: String, page: Int): List<Movie>
}