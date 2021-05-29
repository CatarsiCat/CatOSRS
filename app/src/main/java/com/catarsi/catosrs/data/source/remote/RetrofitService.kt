package com.catarsi.catosrs.data.source.remote

import com.catarsi.catosrs.domain.model.CategoryBase
import com.catarsi.catosrs.domain.model.ItemBase
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("m=itemdb_rs/api/catalogue/category.json")
    fun getCategory(@Query("category") id: Int): Single<CategoryBase>

    @GET("m=itemdb_rs/api/catalogue/category.json")
    fun getCategoryObservable(@Query("category") id: Long): Observable<CategoryBase>

    @GET("m=itemdb_rs/api/catalogue/items.json")
    fun getItems(@Query("category") id: Int, @Query("alpha") alpha: String, @Query("page") page: Int): Single<ItemBase>

    @GET("m=itemdb_rs/api/catalogue/items.json")
    fun getItemsObservable(@Query("category") id: Int, @Query("alpha") alpha: String, @Query("page") page: Int): Observable<ItemBase>
}