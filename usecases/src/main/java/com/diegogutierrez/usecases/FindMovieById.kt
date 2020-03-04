package com.diegogutierrez.usecases

import com.diegogutierrez.data.repository.MoviesRepository
import com.diegogutierrez.domain.Movie

class FindMovieById(private val moviesRepository: MoviesRepository) {
    suspend fun invoke(id: Int): Movie = moviesRepository.findById(id)
}