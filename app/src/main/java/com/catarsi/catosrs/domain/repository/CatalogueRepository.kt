package com.catarsi.catosrs.domain.repository

import com.catarsi.catosrs.data.source.remote.model.Category
import com.catarsi.catosrs.data.source.remote.model.CategoryBase
import com.catarsi.catosrs.data.source.remote.model.Item
import com.catarsi.catosrs.data.source.remote.model.ItemBase
import io.reactivex.Observable
import io.reactivex.Single

interface CatalogueRepository {
    fun getCategory(categoryId: Int?): Single<CategoryBase>
    fun getCategoryObservable(categoryId: Long?): Observable<CategoryBase>
    fun getItems(categoryId: Int?, alpha: String?, page: Int?): Single<ItemBase>
    fun getItemsObservable(categoryId: Int?, alpha: String?, page: Int?): Observable<ItemBase>
    fun getCategoryList(categoriesData: List<CategoryBase>): Single<List<Category>>
    fun getItemsList(results: List<ItemBase>) : Single<List<Item>>
}