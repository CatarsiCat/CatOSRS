package com.catarsi.catosrs.domain.usecase

import com.catarsi.catosrs.data.source.remote.model.Timeserie
import com.catarsi.catosrs.domain.repository.RealTimeRepository
import com.catarsi.catosrs.domain.usecase.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetTimeseriesUseCase @Inject constructor(private val repository: RealTimeRepository) :
    SingleUseCase<List<Timeserie>>() {

    lateinit var timestep: String
    lateinit var id: Integer

    override fun buildUseCaseSingle(): Single<List<Timeserie>> {
        return repository.getTimeseries(timestep, id.toInt()).map { result -> result.data }
    }
}