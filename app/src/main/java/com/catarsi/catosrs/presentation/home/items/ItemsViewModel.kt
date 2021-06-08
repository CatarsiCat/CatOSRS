package com.catarsi.catosrs.presentation.home.items

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.catarsi.catosrs.data.source.remote.model.Category
import com.catarsi.catosrs.domain.entity.CategoryEntity
import com.catarsi.catosrs.data.source.remote.model.Item
import com.catarsi.catosrs.domain.usecase.GetCategoryUseCase
import com.catarsi.catosrs.domain.usecase.GetItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ItemsViewModel  @Inject constructor(private val getCategoryUseCase: GetCategoryUseCase, private val getItemsUseCase: GetItemsUseCase): ViewModel() {
    lateinit var category: CategoryEntity
    val categoryData = MutableLiveData<List<Category>>()
    val items = MutableLiveData<List<Item>>()

    private val TAG = ItemsViewModel::class.java.simpleName

    init {

    }

    internal fun loadCategory() {
        getCategoryUseCase.setCategoryId(category.id)
        getCategoryUseCase.execute(
            onSuccess = {
                categoryData.value = it
                loadItems()
            },
            onError = {
                it.printStackTrace()
            }
        )
    }

    fun loadItems(){
        getItemsUseCase.categoryEntity = category
        getItemsUseCase.categoryData = categoryData.value!!

        getItemsUseCase.execute(
            onSuccess = {
                items.value = it
                Log.d(TAG, it.toString())
            },
            onError = {
                Log.e(TAG, it.printStackTrace().toString())
            }
        )
    }
}