package com.diegogutierrez.usecases

import com.diegogutierrez.data.repository.MoviesRepository
import com.diegogutierrez.domain.Movie

class ToggleMovieWatchLater(private val moviesRepository: MoviesRepository) {
    suspend fun invoke(movie: Movie): Movie = with(movie) {
        copy(watchLater = !watchLater).also { moviesRepository.update(it) }
    }
}