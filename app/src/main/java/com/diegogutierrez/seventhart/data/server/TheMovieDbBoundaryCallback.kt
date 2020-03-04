package com.diegogutierrez.seventhart.data.server

import androidx.paging.PagedList
//import androidx.paging.PagingRequestHelper

/**
 * This boundary callback gets notified when user reaches to the edges of the list such that the
 * database cannot provide any more data.
 * <p>
 * The boundary callback might be called multiple times for the same direction so it does its own
 * rate limiting using the PagingRequestHelper class.
 */
class TheMovieDbBoundaryCallback {
}