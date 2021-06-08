package com.catarsi.catosrs.data.source.remote.model

import com.catarsi.catosrs.domain.entity.LatestPriceEntity

data class LatestPrice (val high: Long,
                        val highTime: Long,
                        val low: Long,
                        val lowTime: Long) {

    fun toLatestPriceEntity(id: Int) = LatestPriceEntity(
        id = id,
        high = high,
        highTime = highTime,
        low = low,
        lowTime = lowTime
    )
}