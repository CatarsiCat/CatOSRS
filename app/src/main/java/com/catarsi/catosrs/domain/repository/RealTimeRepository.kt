package com.catarsi.catosrs.domain.repository

import io.reactivex.Single

interface RealTimeRepository {
    fun getLatestPrices(): Single<String>
}