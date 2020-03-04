package com.diegogutierrez.usecases

import com.diegogutierrez.data.repository.MoviesRepository
import com.diegogutierrez.domain.Movie

class ToggleMovieFavorite(private val moviesRepository: MoviesRepository) {
    suspend fun invoke(movie: Movie): Movie = with(movie) {
        copy(favorite = !favorite).also { moviesRepository.update(it) }
    }
}