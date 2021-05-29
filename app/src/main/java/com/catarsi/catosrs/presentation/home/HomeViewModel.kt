package com.catarsi.catosrs.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.catarsi.catosrs.domain.model.CategoryBase
import com.catarsi.catosrs.domain.model.CategoryEntity
import com.catarsi.catosrs.domain.usecase.GetAllCategoriesUseCase
import com.catarsi.catosrs.domain.usecase.GetCategoryUseCase
import com.catarsi.catosrs.util.Categories
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getAllCategoriesUseCase: GetAllCategoriesUseCase) : ViewModel() {

    private val TAG = HomeViewModel::class.java.simpleName
    val categoryList: MutableList<CategoryEntity> = mutableListOf()


    init {
        setCategoryList()
    }

    private fun setCategoryList() {
        enumValues<Categories>().forEach { categoryList.add(CategoryEntity(it.title, it.id)) }
    }

    fun loadCategory() {
        getAllCategoriesUseCase.execute(
                onSuccess = {
                    println(it)
                },
                onError = {
                    it.printStackTrace()
                }
        )
    }

}
