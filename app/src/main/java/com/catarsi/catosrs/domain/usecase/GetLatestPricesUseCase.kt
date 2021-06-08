package com.catarsi.catosrs.domain.usecase

import com.catarsi.catosrs.data.source.remote.model.toLatestPriceEntityList
import com.catarsi.catosrs.domain.entity.LatestPriceEntity
import com.catarsi.catosrs.domain.repository.RealTimeRepository
import com.catarsi.catosrs.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetLatestPricesUseCase @Inject constructor(private val repository: RealTimeRepository) :
    SingleUseCase<List<LatestPriceEntity>>() {

    override fun buildUseCaseSingle(): Single<List<LatestPriceEntity>> {

        return repository.getLatestPrices().map {
                result ->
            result.toLatestPriceEntityList()
        }
    }

}