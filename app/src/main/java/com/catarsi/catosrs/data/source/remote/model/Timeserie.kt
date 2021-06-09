package com.catarsi.catosrs.data.source.remote.model

data class Timeserie (var timestamp: Int,
                      var avgHighPrice: Int,
                      var avgLowPrice: Int,
                      var highPriceVolume: Int,
                      var lowPriceVolume: Int,
)