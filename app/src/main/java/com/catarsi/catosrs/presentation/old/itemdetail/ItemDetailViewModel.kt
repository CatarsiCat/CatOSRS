package com.catarsi.catosrs.presentation.old.itemdetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.catarsi.catosrs.data.source.remote.model.OldItem
import com.catarsi.catosrs.data.source.remote.model.Timeserie
import com.catarsi.catosrs.domain.usecase.GetTimeseriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ItemDetailViewModel @Inject constructor(private val getTimeseriesUseCase: GetTimeseriesUseCase) :
    ViewModel() {


    val timeseries = MutableLiveData<List<Timeserie>>()
    lateinit var oldItem: OldItem

    private val TAG = ItemDetailViewModel::class.java.simpleName

    init {

    }

    fun getTimeseries() {
        getTimeseriesUseCase.id = oldItem.id
        getTimeseriesUseCase.timestep = "5m"
        getTimeseriesUseCase.execute(
            onSuccess = {
                timeseries.value = it
            },
            onError = {
                Log.e(TAG, it.printStackTrace().toString())
            }
        )
    }
}