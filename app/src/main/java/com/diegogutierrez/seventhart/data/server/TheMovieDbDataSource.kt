package com.diegogutierrez.seventhart.data.server

import com.diegogutierrez.data.source.RemoteDataSource
import com.diegogutierrez.seventhart.data.toDomainMovie
import com.diegogutierrez.domain.Movie

class TheMovieDbDataSource(private val theMovieDb: TheMovieDb) : RemoteDataSource {

    override suspend fun getPopularMovies(apiKey: String, region: String): List<Movie> =
        theMovieDb.service
            .listPopularMoviesAsync(apiKey, region).await()
            .results
            .map { it.toDomainMovie() }
}