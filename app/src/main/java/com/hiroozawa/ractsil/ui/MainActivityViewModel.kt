package com.hiroozawa.ractsil.ui

import androidx.lifecycle.*
import com.hiroozawa.ractsil.domain.Car
import com.hiroozawa.ractsil.data.CarRepository
import com.hiroozawa.ractsil.data.Result
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val carRepository: CarRepository
) : ViewModel() {

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _cars = MutableLiveData<List<Car>>().apply { value = emptyList() }
    val cars: LiveData<List<Car>> = _cars

    private val _errorLabel = MutableLiveData<Boolean>()
    val errorLabel: LiveData<Boolean> = _errorLabel

    val empty: LiveData<Boolean> = Transformations.map(_cars) {
        it.isEmpty()
    }

    fun load() {
        _dataLoading.value = true

        viewModelScope.launch {
            when (val result = carRepository.fetchCars()) {
                is Result.Success -> _cars.value = result.data
                is Result.Error -> _errorLabel.value = true
            }
        }

        _dataLoading.value = false
    }

    fun refresh() {

    }


}