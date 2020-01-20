package com.diegogutierrez.seventhart.data

import com.diegogutierrez.domain.Movie
import com.diegogutierrez.seventhart.data.database.Movie as RoomMovie
import com.diegogutierrez.seventhart.data.server.Movie as ServerMovie

fun Movie.toRoomMovie(): RoomMovie =
    RoomMovie(
        id,
        title,
        overview,
        releaseDate,
        posterPath,
        backdropPath,
        originalLanguage,
        originalTitle,
        popularity,
        voteAverage,
        favorite
    )

fun RoomMovie.toDomainMovie(): Movie = Movie(
    id,
    title,
    overview,
    releaseDate,
    posterPath,
    backdropPath,
    originalLanguage,
    originalTitle,
    popularity,
    voteAverage,
    favorite
)

fun ServerMovie.toDomainMovie(): Movie =
    Movie(
        0,
        title,
        overview,
        releaseDate,
        posterPath,
        backdropPath ?: posterPath,
        originalLanguage,
        originalTitle,
        popularity,
        voteAverage,
        false
    )