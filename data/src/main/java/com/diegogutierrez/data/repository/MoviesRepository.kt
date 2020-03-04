package com.diegogutierrez.data.repository

import com.diegogutierrez.data.source.LocalDataSource
import com.diegogutierrez.data.source.RemoteDataSource
import com.diegogutierrez.domain.Movie

class MoviesRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val regionRepository: RegionRepository,
    private val apiKey: String
) {

    suspend fun getPopularMovies(page: Int): List<Movie> {

        //if (localDataSource.isEmpty()) {
        val movies =
            remoteDataSource.getPopularMovies(apiKey, regionRepository.findLastRegion(), page)
        localDataSource.saveMovies(movies)
        //}

        return localDataSource.getPopularMovies()
    }

    suspend fun findById(id: Int): Movie = localDataSource.findById(id)

    suspend fun update(movie: Movie) = localDataSource.update(movie)
}