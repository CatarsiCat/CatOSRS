package com.catarsi.catosrs.domain.repository

import com.catarsi.catosrs.domain.model.Category
import com.catarsi.catosrs.domain.model.CategoryBase
import io.reactivex.Single

interface CatalogueRepository {
    fun getCategory(categoryId: Long?): Single<CategoryBase>
}