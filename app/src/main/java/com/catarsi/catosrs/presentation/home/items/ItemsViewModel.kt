package com.catarsi.catosrs.presentation.home.items

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.catarsi.catosrs.domain.model.CategoryBase
import com.catarsi.catosrs.domain.model.CategoryEntity
import com.catarsi.catosrs.domain.usecase.GetCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ItemsViewModel  @Inject constructor(private val getCategoryUseCase: GetCategoryUseCase): ViewModel() {
    lateinit var category: CategoryEntity
    val categoryData = MutableLiveData<CategoryBase>()

    private val TAG = ItemsViewModel::class.java.simpleName

    init {

    }

    internal fun loadCategory() {
        getCategoryUseCase.setCategoryId(category.id)
        getCategoryUseCase.execute(
            onSuccess = {
                categoryData.value = it
            },
            onError = {
                it.printStackTrace()
            }
        )
    }
}