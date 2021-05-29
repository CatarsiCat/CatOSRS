package com.catarsi.catosrs.presentation.home.items

import androidx.lifecycle.ViewModel
import com.catarsi.catosrs.domain.model.CategoryEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ItemsViewModel @Inject constructor(): ViewModel() {
    lateinit var category: CategoryEntity

    private val TAG = ItemsViewModel::class.java.simpleName

    init {

    }
}