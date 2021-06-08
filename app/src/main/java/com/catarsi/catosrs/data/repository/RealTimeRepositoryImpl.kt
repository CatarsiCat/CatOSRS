package com.catarsi.catosrs.data.repository

import com.catarsi.catosrs.data.source.remote.RetrofitOldService
import com.catarsi.catosrs.domain.repository.RealTimeRepository
import io.reactivex.Single

class RealTimeRepositoryImpl(private val retrofitOldService: RetrofitOldService) :
    RealTimeRepository {
    override fun getLatestPrices(): Single<String> {
        return retrofitOldService.getLatestPrices()
    }
}