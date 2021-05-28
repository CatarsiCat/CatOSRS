package com.catarsi.catosrs.data.repository

import com.catarsi.catosrs.data.source.remote.RetrofitService
import com.catarsi.catosrs.domain.model.Category
import com.catarsi.catosrs.domain.model.CategoryBase
import com.catarsi.catosrs.domain.repository.CatalogueRepository
import io.reactivex.Single

class CatalogueRepositoryImpl (private val retrofitService: RetrofitService) : CatalogueRepository{
    override fun getCategory(categoryId: Long?): Single<CategoryBase> {
        return retrofitService.getCategory(categoryId!!);
    }

}