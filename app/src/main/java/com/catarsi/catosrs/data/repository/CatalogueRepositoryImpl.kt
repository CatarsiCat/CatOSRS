package com.catarsi.catosrs.data.repository

import com.catarsi.catosrs.data.source.remote.RetrofitService
import com.catarsi.catosrs.domain.model.Category
import com.catarsi.catosrs.domain.model.CategoryBase
import com.catarsi.catosrs.domain.model.ItemBase
import com.catarsi.catosrs.domain.repository.CatalogueRepository
import io.reactivex.Observable
import io.reactivex.Single

class CatalogueRepositoryImpl(private val retrofitService: RetrofitService) : CatalogueRepository {
    override fun getCategory(categoryId: Int?): Single<CategoryBase> {
        return retrofitService.getCategory(categoryId!!)
    }

    override fun getCategoryObservable(categoryId: Long?): Observable<CategoryBase> {
        return retrofitService.getCategoryObservable(categoryId!!)
    }

    override fun getItems(categoryId: Long?, alpha: String?, page: Int?): Single<ItemBase> {
        return retrofitService.getItems(categoryId!!, alpha!!, page!!)
    }

    override fun getCategoryList(categoriesData: List<CategoryBase>): Single<List<Category>> {
        val categoryList = mutableListOf<Category>()
        return Single.defer {
            for (categoryBase in categoriesData) {
                categoryList.addAll(categoryBase.alpha)
            }
            Single.just(categoryList)
        }
    }


}