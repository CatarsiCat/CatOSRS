package com.catarsi.catosrs.domain.usecase

import com.catarsi.catosrs.data.source.remote.model.Category
import com.catarsi.catosrs.domain.entity.CategoryEntity
import com.catarsi.catosrs.data.source.remote.model.Item
import com.catarsi.catosrs.data.source.remote.model.ItemBase
import com.catarsi.catosrs.domain.repository.CatalogueRepository
import com.catarsi.catosrs.domain.usecase.base.SingleUseCase
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class GetItemsUseCase @Inject constructor(private val repository: CatalogueRepository) : SingleUseCase<List<Item>>() {

    lateinit var categoryEntity: CategoryEntity
    internal lateinit var categoryData: List<Category>

    override fun buildUseCaseSingle(): Single<List<Item>> {
        val requests = ArrayList< Observable <ItemBase>>()
        for(category in categoryData){
            if(category.items > 0) {
                val numberOfPages: Int = (category.items / 12)
                for (i in 0..numberOfPages) {
                    if (category.letter == "#") {
                        requests.add(repository.getItemsObservable(categoryEntity.id, "%23", i + 1))
                    } else {
                        requests.add(
                            repository.getItemsObservable(
                                categoryEntity.id,
                                category.letter,
                                i + 1
                            )
                        )
                    }
                }
            }
        }

        return Observable.fromIterable(requests)
            .flatMap { task -> task.observeOn(Schedulers.computation()) }
            .toList()
            .flatMap { results -> repository.getItemsList(results)}
    }
}