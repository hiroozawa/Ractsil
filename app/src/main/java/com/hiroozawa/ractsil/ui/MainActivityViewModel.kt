package com.hiroozawa.ractsil.ui

import androidx.lifecycle.*
import com.hiroozawa.ractsil.R
import com.hiroozawa.ractsil.data.CarRepository
import com.hiroozawa.ractsil.data.Result
import com.hiroozawa.ractsil.domain.Car
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val carRepository: CarRepository
) : ViewModel() {

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _cars = MutableLiveData<List<Car>>().apply { value = emptyList() }
    val cars: LiveData<List<Car>> = _cars

    private val _errorEvent = MutableLiveData<Event<Int>>()
    val errorEvent: LiveData<Event<Int>> = _errorEvent

    val empty: LiveData<Boolean> = Transformations.map(_cars) {
        it.isEmpty()
    }

    init {
        load()
    }

    private fun load() {
        _dataLoading.value = true

        viewModelScope.launch {
            when (val result = carRepository.fetchCars()) {
                is Result.Success -> _cars.value = result.data
                is Result.Error -> _errorEvent.value = Event(R.string.error)
            }
            _dataLoading.value = false
        }
    }

    fun refresh() {
        load()
    }
}