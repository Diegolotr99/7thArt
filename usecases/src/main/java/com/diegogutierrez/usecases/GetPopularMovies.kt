package com.diegogutierrez.usecases

import com.diegogutierrez.data.repository.MoviesRepository
import com.diegogutierrez.domain.Movie

class GetPopularMovies(private val moviesRepository: MoviesRepository) {
    suspend fun invoke(page: Int): List<Movie> = moviesRepository.getPopularMovies(page)
}