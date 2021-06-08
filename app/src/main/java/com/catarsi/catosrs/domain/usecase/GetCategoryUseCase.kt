package com.catarsi.catosrs.domain.usecase

import com.catarsi.catosrs.data.source.remote.model.Category
import com.catarsi.catosrs.domain.repository.CatalogueRepository
import com.catarsi.catosrs.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetCategoryUseCase @Inject constructor(private val repository: CatalogueRepository) : SingleUseCase<List<Category>>() {

    private var categoryId: Int? = null

    fun setCategoryId(id: Int){
        categoryId = id
    }

    override fun buildUseCaseSingle(): Single<List<Category>> {
        return repository.getCategory(categoryId).map { it.alpha }
    }

}