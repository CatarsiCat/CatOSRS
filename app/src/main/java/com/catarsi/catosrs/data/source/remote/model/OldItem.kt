package com.catarsi.catosrs.data.source.remote.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OldItem(
    var id: Integer,
    var members: Boolean,
    var lowatclh: Int,
    var limit: Int,
    var value: Int,
    var highalch: Int,
    var icon: String,
    var name: String
) : Parcelable