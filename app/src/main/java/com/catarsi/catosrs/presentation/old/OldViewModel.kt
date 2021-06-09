package com.catarsi.catosrs.presentation.old

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.catarsi.catosrs.data.source.remote.model.OldItem
import com.catarsi.catosrs.domain.usecase.GetLatestPricesUseCase
import com.catarsi.catosrs.domain.usecase.GetMappingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OldViewModel @Inject constructor(
    private val getLatestPricesUseCase: GetLatestPricesUseCase,
    private val getMappingUseCase: GetMappingUseCase
) :
    ViewModel() {

    private val TAG = OldViewModel::class.java.simpleName
    var listItem = MutableLiveData<List<OldItem>>()


    fun loadRepo() {
/*
        getLatestPricesUseCase.execute(
            onSuccess = {
                println(it)
            },
            onError = {
                it.printStackTrace()
            }
        )
  */
        getMappingUseCase.execute(
            onSuccess = {
                listItem.value = it
            },
            onError = {
                it.printStackTrace()
            }
        )

    }
}