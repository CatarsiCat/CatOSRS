package com.catarsi.catosrs.domain.repository

import com.catarsi.catosrs.data.source.remote.model.LatestPricesBase
import com.catarsi.catosrs.data.source.remote.model.OldItem
import com.catarsi.catosrs.data.source.remote.model.TimeseriesBase
import io.reactivex.Single

interface RealTimeRepository {
    fun getLatestPrices(): Single<LatestPricesBase>
    fun getMapping(): Single<List<OldItem>>
    fun getTimeseries(timestep: String?, id: Int?): Single<TimeseriesBase>
}
