package com.catarsi.catosrs.domain.repository

import com.catarsi.catosrs.data.source.remote.model.LatestPricesBase
import io.reactivex.Observable
import io.reactivex.Single

interface RealTimeRepository {
    fun getLatestPrices(): Single<LatestPricesBase>
}
