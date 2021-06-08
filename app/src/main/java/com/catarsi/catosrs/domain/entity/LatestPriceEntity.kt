package com.catarsi.catosrs.domain.entity

data class LatestPriceEntity (val id: Int,
                              val high: Long,
                              val highTime: Long,
                              val low: Long,
                              val lowTime: Long)