package com.catarsi.catosrs.domain.usecase

import com.catarsi.catosrs.domain.repository.RealTimeRepository
import com.catarsi.catosrs.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetLatestPricesUseCase @Inject constructor(private val repository: RealTimeRepository) :
    SingleUseCase<String>() {

    override fun buildUseCaseSingle(): Single<String> {
        return repository.getLatestPrices()
    }

}