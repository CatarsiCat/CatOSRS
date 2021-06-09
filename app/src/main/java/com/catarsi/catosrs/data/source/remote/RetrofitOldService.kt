package com.catarsi.catosrs.data.source.remote


import com.catarsi.catosrs.data.source.remote.model.LatestPricesBase
import com.catarsi.catosrs.data.source.remote.model.OldItem
import com.catarsi.catosrs.data.source.remote.model.TimeseriesBase
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface RetrofitOldService {
    @GET("latest")
    fun getLatestPrices(): Single<LatestPricesBase>

    @GET("mapping")
    fun getMapping(): Single<List<OldItem>>

    @GET("timeseries")
    fun getTimeseries(
        @Query("timestep") timestep: String,
        @Query("id") id: Int
    ): Single<TimeseriesBase>
}