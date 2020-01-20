package com.diegogutierrez.data.source

interface LocationDataSource {
    suspend fun findLastRegion(): String?
}