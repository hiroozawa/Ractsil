package com.hiroozawa.ractsil.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hiroozawa.ractsil.data.remote.CarRemoteDataSource
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val carRemoteDataSource: CarRemoteDataSource
) : ViewModel(){

    fun load(){
        viewModelScope.launch {
            carRemoteDataSource.fetchCars()
        }
    }

    fun printSome(className: String){
        print("fromfragment $className")
    }

}