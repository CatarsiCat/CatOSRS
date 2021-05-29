package com.catarsi.catosrs.domain.model

data class Item (
        var icon: String,
        var icon_large: String,
        var id: Int,
        var type: String,
        var typeIcon: String,
        var name: String,
        var description: String,
        var current: ItemTrend,
        var today: ItemTrend,
        var trend: String,
        var price: String,
        var member: Boolean
        )