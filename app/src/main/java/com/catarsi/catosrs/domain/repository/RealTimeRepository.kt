package com.catarsi.catosrs.domain.repository

import com.catarsi.catosrs.data.source.remote.model.LatestPricesBase
import com.catarsi.catosrs.data.source.remote.model.OldItem
import io.reactivex.Single

interface RealTimeRepository {
    fun getLatestPrices(): Single<LatestPricesBase>
    fun getMapping(): Single<List<OldItem>>
}
