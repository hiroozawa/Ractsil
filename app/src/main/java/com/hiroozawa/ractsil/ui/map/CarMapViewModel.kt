package com.hiroozawa.ractsil.ui.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hiroozawa.ractsil.R
import com.hiroozawa.ractsil.data.CarRepository
import com.hiroozawa.ractsil.data.Result
import com.hiroozawa.ractsil.domain.Car
import com.hiroozawa.ractsil.ui.Event
import com.hiroozawa.ractsil.ui.util.wrapEspressoIdlingResource
import kotlinx.coroutines.launch
import javax.inject.Inject

class CarMapViewModel @Inject constructor(
    private val carRepository: CarRepository
) : ViewModel() {

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _cars = MutableLiveData<List<Car>>().apply { value = emptyList() }
    val cars: LiveData<List<Car>> = _cars

    private val _openCarListEvent = MutableLiveData<Event<String>>()
    val openCarListEvent: LiveData<Event<String>> = _openCarListEvent

    private val _errorEvent = MutableLiveData<Event<Int>>()
    val errorEvent: LiveData<Event<Int>> = _errorEvent

    init {
        load()
    }

    private fun load() {
        _dataLoading.value = true

        wrapEspressoIdlingResource {
            viewModelScope.launch {
                when (val result = carRepository.fetchCars()) {
                    is Result.Success -> _cars.value = result.data
                    is Result.Error -> _errorEvent.value = Event(R.string.error)
                }
                _dataLoading.value = false
            }
        }
    }


    fun openCarEvent(carId: String) {
        _openCarListEvent.value = Event(carId)
    }
}