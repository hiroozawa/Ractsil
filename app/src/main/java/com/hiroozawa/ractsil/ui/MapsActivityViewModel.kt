package com.hiroozawa.ractsil.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hiroozawa.ractsil.remote.CarRemoteDataSource
import kotlinx.coroutines.launch
import javax.inject.Inject

class MapsActivityViewModel @Inject constructor(
    private val carRemoteDataSource: CarRemoteDataSource
) : ViewModel(){

    fun load(){
        viewModelScope.launch {
            carRemoteDataSource.getCars()
        }
    }

}