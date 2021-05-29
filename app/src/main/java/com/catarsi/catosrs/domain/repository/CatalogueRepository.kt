package com.catarsi.catosrs.domain.repository

import com.catarsi.catosrs.domain.model.Category
import com.catarsi.catosrs.domain.model.CategoryBase
import com.catarsi.catosrs.domain.model.ItemBase
import io.reactivex.Observable
import io.reactivex.Single

interface CatalogueRepository {
    fun getCategory(categoryId: Int?): Single<CategoryBase>
    fun getCategoryObservable(categoryId: Long?): Observable<CategoryBase>
    fun getItems(categoryId: Long?, alpha: String?, page: Int?): Single<ItemBase>
    fun getCategoryList(categoriesData: List<CategoryBase>): Single<List<Category>>
}