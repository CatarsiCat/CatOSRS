package com.catarsi.catosrs.data.repository

import com.catarsi.catosrs.data.source.remote.RetrofitOldService
import com.catarsi.catosrs.data.source.remote.model.LatestPricesBase
import com.catarsi.catosrs.data.source.remote.model.OldItem
import com.catarsi.catosrs.domain.repository.RealTimeRepository
import io.reactivex.Single

class RealTimeRepositoryImpl(private val retrofitOldService: RetrofitOldService) :
    RealTimeRepository {
    override fun getLatestPrices(): Single<LatestPricesBase> {
        return retrofitOldService.getLatestPrices()
    }

    override fun getMapping(): Single<List<OldItem>> {
        return retrofitOldService.getMapping()
    }
}