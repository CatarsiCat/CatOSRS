package com.catarsi.catosrs.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.catarsi.catosrs.domain.model.Category
import com.catarsi.catosrs.domain.model.CategoryBase
import com.catarsi.catosrs.domain.usecase.GetAllCategoriesUseCase
import com.catarsi.catosrs.domain.usecase.GetCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getCategoryUseCase: GetCategoryUseCase, private val getAllCategoriesUseCase: GetAllCategoriesUseCase) : ViewModel(){

    private val TAG = HomeViewModel::class.java.simpleName
    val categoryData = MutableLiveData<CategoryBase>()

    init {

    }

    fun loadCategory(){
        getCategoryUseCase.setCategoryId(9)
        getCategoryUseCase.execute(
            onSuccess = {
                categoryData.value = it
            },
            onError = {
                it.printStackTrace()
            }
        )

        getAllCategoriesUseCase.execute(
                onSuccess = {
                    println(it)
                },
                onError = {
                    it.printStackTrace()
                }
        )
    }


    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}
