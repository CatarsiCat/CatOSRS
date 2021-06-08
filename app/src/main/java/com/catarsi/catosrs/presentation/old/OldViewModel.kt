package com.catarsi.catosrs.presentation.old

import androidx.lifecycle.ViewModel
import com.catarsi.catosrs.domain.usecase.GetLatestPricesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OldViewModel @Inject constructor(private val getLatestPricesUseCase: GetLatestPricesUseCase) :
    ViewModel() {

    private val TAG = OldViewModel::class.java.simpleName

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
    }
}