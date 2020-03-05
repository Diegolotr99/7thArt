package com.diegogutierrez.seventhart

import android.app.Application
import com.diegogutierrez.data.repository.MoviesRepository
import com.diegogutierrez.data.repository.PermissionChecker
import com.diegogutierrez.data.repository.RegionRepository
import com.diegogutierrez.data.source.LocalDataSource
import com.diegogutierrez.data.source.LocationDataSource
import com.diegogutierrez.data.source.RemoteDataSource
import com.diegogutierrez.seventhart.data.AndroidPermissionChecker
import com.diegogutierrez.seventhart.data.PlayServicesLocationDataSource
import com.diegogutierrez.seventhart.data.database.RoomDataSource
import com.diegogutierrez.seventhart.data.database.SeventhArtDatabase
import com.diegogutierrez.seventhart.data.server.TheMovieDb
import com.diegogutierrez.seventhart.data.server.TheMovieDbDataSource
import com.diegogutierrez.seventhart.ui.detail.DetailActivity
import com.diegogutierrez.seventhart.ui.detail.DetailViewModel
import com.diegogutierrez.seventhart.ui.main.MainActivity
import com.diegogutierrez.seventhart.ui.main.MainViewModel
import com.diegogutierrez.usecases.FindMovieById
import com.diegogutierrez.usecases.GetPopularMovies
import com.diegogutierrez.usecases.ToggleMovieFavorite
import com.diegogutierrez.usecases.ToggleMovieWatchLater
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun Application.initDI() {
    startKoin {
        androidLogger()
        androidContext(this@initDI)
        modules(listOf(appModule, dataModule, scopesModule))
    }
}

private val appModule = module {
    single(named("tmdbApiKey")) { BuildConfig.TMDB_API_KEY }
    single { SeventhArtDatabase.build(get()) }
    factory<LocalDataSource> { RoomDataSource(get()) }
    factory<RemoteDataSource> { TheMovieDbDataSource(get()) }
    factory<LocationDataSource> { PlayServicesLocationDataSource(get()) }
    factory<PermissionChecker> { AndroidPermissionChecker(get()) }
    single<CoroutineDispatcher> { Dispatchers.Main }
    single(named("baseUrl")) { "https://api.themoviedb.org/3/" }
    single { TheMovieDb(get(named("baseUrl"))) }
}

val dataModule = module {
    factory { RegionRepository(get(), get()) }
    factory { MoviesRepository(get(), get(), get(), get(named("tmdbApiKey"))) }
}

private val scopesModule = module {
    scope(named<MainActivity>()) {
        viewModel { MainViewModel(get(), get()) }
        scoped { GetPopularMovies(get()) }
    }

    scope(named<DetailActivity>()) {
        viewModel { (id: Int) -> DetailViewModel(id, get(), get(), get(), get()) }
        scoped { FindMovieById(get()) }
        scoped { ToggleMovieFavorite(get()) }
        scoped { ToggleMovieWatchLater(get()) }
    }
}