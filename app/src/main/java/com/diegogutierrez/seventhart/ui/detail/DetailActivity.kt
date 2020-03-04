package com.diegogutierrez.seventhart.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import com.diegogutierrez.domain.Movie
import com.diegogutierrez.seventhart.R
import com.diegogutierrez.seventhart.ui.common.loadUrl
import com.diegogutierrez.seventhart.ui.common.showLongSnackBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.leinardi.android.speeddial.SpeedDialActionItem
import com.leinardi.android.speeddial.SpeedDialView
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.scope.currentScope
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetailActivity : AppCompatActivity() {

    companion object {
        const val MOVIE = "DetailActivity:movie"
    }

    private val viewModel: DetailViewModel by currentScope.viewModel(this) {
        parametersOf(intent.getIntExtra(MOVIE, -1))
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        viewModel.model.observe(this, Observer(::updateUi))

        setFloatingMenu(null)
    }

    private fun setFloatingMenu(movie: Movie?) {
        val iconFavorite = if (movie!=null && movie.favorite) R.drawable.ic_favorite_on else R.drawable.ic_favorite_off
        val iconWatchLater = if (movie!=null && movie.watchLater) R.drawable.ic_watch_later_on else R.drawable.ic_watch_later_off_black

        val speedDialView = speedDial
        speedDialView.addActionItem(
            SpeedDialActionItem.Builder(R.id.fab_favorite, iconFavorite)
                .setFabSize(FloatingActionButton.SIZE_NORMAL)
                .setFabBackgroundColor(ResourcesCompat.getColor(resources, R.color.colorAccent, theme))
                .create())

        speedDialView.addActionItem(
            SpeedDialActionItem.Builder(R.id.fab_watch_later, iconWatchLater)
                .setFabSize(FloatingActionButton.SIZE_NORMAL)
                .setFabBackgroundColor(ResourcesCompat.getColor(resources, R.color.colorAccent, theme))
                .create())

        // Set option FAB's click listeners.
        speedDialView.setOnActionSelectedListener { actionItem ->
            when (actionItem.id) {
                R.id.fab_favorite -> { viewModel.onFavoriteClicked().invokeOnCompletion {
                        if(movie?.favorite == true){
                            showLongSnackBar(movieDetailInfo, "Removed from Favorites list")
                        }else if(movie?.favorite == false){
                            showLongSnackBar(movieDetailInfo, "Saved to Favorites list")
                        }
                    }
                }
                R.id.fab_watch_later -> {
                    viewModel.onWatchLaterClicked().invokeOnCompletion {
                        if(movie?.watchLater == true){
                            showLongSnackBar(movieDetailInfo, "Removed from Watch Later list")
                        }else if(movie?.watchLater == false){
                            showLongSnackBar(movieDetailInfo, "Saved to Watch Later list")
                        }
                    }
                }
            }
            true
        }
    }

    private fun updateUi(model: DetailViewModel.UiModel) = with(model.movie) {
        movieDetailToolbar.title = title
        val moviePoster = if (backdropPath != "") {
            "https://image.tmdb.org/t/p/w780/${backdropPath}"
        } else {
            "https://i.ibb.co/Lt37RWS/7th-art-logo-copy.png"
        }
        movieDetailImage.loadUrl(moviePoster)
        movieDetailSummary.text = overview
        movieDetailInfo.setMovie(this)

        // Update FAB menu options
        setFloatingMenu(this)
    }
}
