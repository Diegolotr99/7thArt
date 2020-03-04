package com.diegogutierrez.seventhart.ui.common

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun showLongSnackBar(view: View, text: String) {
    val snackBar = Snackbar.make(view, text, Snackbar.LENGTH_LONG)
    snackBar.setAction("Close"){
        snackBar.dismiss()
    }
    snackBar.show()
}

fun showShortSnackBar(view: View, text: String) {
    val snackBar = Snackbar.make(view, text, Snackbar.LENGTH_SHORT)
    snackBar.setAction("Close"){
        snackBar.dismiss()
    }
    snackBar.show()
}

fun showIndefiniteSnackBar(view: View, text: String) {
    val snackBar = Snackbar.make(view, text, Snackbar.LENGTH_INDEFINITE)
    snackBar.setAction("Close"){
        snackBar.dismiss()
    }
    snackBar.show()
}