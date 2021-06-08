package com.catarsi.catosrs.data.source.remote


import com.catarsi.catosrs.data.source.remote.model.LatestPricesBase
import io.reactivex.Single
import retrofit2.http.GET


interface RetrofitOldService {
    @GET("latest")
    fun getLatestPrices(): Single<LatestPricesBase>
}