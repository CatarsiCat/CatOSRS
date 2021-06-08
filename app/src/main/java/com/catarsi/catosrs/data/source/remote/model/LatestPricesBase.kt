package com.catarsi.catosrs.data.source.remote.model

data class LatestPricesBase (val data: Map<String, LatestPrice>)

fun LatestPricesBase.toLatestPriceEntityList() = data.toList().map {
    it.second.toLatestPriceEntity(Integer.parseInt(it.first))
}

