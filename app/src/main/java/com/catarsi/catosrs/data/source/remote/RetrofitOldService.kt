package com.catarsi.catosrs.data.source.remote


import io.reactivex.Single
import retrofit2.http.GET


interface RetrofitOldService {
    @GET("latest")
    fun getLatestPrices(): Single<String>
}