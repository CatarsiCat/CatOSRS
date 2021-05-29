package com.catarsi.catosrs.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CategoryEntity(var name: String, var id: Int) : Parcelable