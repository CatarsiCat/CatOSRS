package com.catarsi.catosrs.domain.usecase

import com.catarsi.catosrs.data.source.remote.model.Category
import com.catarsi.catosrs.data.source.remote.model.CategoryBase
import com.catarsi.catosrs.domain.repository.CatalogueRepository
import com.catarsi.catosrs.domain.usecase.base.SingleUseCase
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetAllCategoriesUseCase @Inject constructor(private val repository: CatalogueRepository) : SingleUseCase<List<Category>>() {


    override fun buildUseCaseSingle(): Single<List<Category>> {

        val requests = ArrayList<Observable<CategoryBase>>()
        requests.add(repository.getCategoryObservable(0))
        requests.add(repository.getCategoryObservable(1))
        requests.add(repository.getCategoryObservable(2))
        //TODO Add more
        return Observable.fromIterable(requests)
                .flatMap { task -> task.observeOn(Schedulers.computation()) }
                .toList()
                .flatMap { results -> repository.getCategoryList(results) }
    }


}