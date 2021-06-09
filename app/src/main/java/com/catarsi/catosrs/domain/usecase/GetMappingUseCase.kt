package com.catarsi.catosrs.domain.usecase

import com.catarsi.catosrs.data.source.remote.model.OldItem
import com.catarsi.catosrs.domain.repository.RealTimeRepository
import com.catarsi.catosrs.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetMappingUseCase @Inject constructor(private val repository: RealTimeRepository) :
    SingleUseCase<List<OldItem>>() {
    override fun buildUseCaseSingle(): Single<List<OldItem>> {
        return repository.getMapping()
    }
}