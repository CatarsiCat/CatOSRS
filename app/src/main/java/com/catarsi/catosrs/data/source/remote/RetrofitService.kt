package com.catarsi.catosrs.data.source.remote

import com.catarsi.catosrs.domain.model.Category
import com.catarsi.catosrs.domain.model.CategoryBase
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {

    @GET("m=itemdb_rs/api/catalogue/category.json")
    fun getCategory(@Query("category")id: Long): Single<CategoryBase>
}