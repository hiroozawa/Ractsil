package com.hiroozawa.ractsil.ui

import androidx.lifecycle.*
import com.hiroozawa.ractsil.R
import com.hiroozawa.ractsil.data.CarRepository
import com.hiroozawa.ractsil.data.Result
import com.hiroozawa.ractsil.domain.Car
import com.hiroozawa.ractsil.ui.list.CarUiModel
import com.hiroozawa.ractsil.ui.list.CarUiModelMapper
import com.hiroozawa.ractsil.ui.util.wrapEspressoIdlingResource
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val carRepository: CarRepository
) : ViewModel() {

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _cars = MutableLiveData<List<Car>>().apply { value = emptyList() }
    val cars: LiveData<List<Car>> = _cars

    private val _carsUiModel = MutableLiveData<List<CarUiModel>>()
        .apply { value = emptyList() }
    val carsUiModel: LiveData<List<CarUiModel>> = _carsUiModel

    private val _errorEvent = MutableLiveData<Event<Int>>()
    val errorEvent: LiveData<Event<Int>> = _errorEvent

    val empty: LiveData<Boolean> = Transformations.map(_carsUiModel) {
        it.isEmpty()
    }

    private val _openCarMapEvent = MutableLiveData<Event<String>>()
    val openCarMapEvent: LiveData<Event<String>> = _openCarMapEvent

    init {
        load()
    }

    private fun load(forceUpdate: Boolean = false) {
        _dataLoading.value = true

        wrapEspressoIdlingResource {
            viewModelScope.launch {
                when (val result = carRepository.fetchCars(forceUpdate)) {
                    is Result.Success -> {
                        _carsUiModel.value = CarUiModelMapper(result.data)
                        _cars.value = result.data
                    }
                    is Result.Error -> _errorEvent.value = Event(R.string.error)
                }
                _dataLoading.value = false
            }
        }
    }

    fun refresh() {
        load(forceUpdate = true)
    }

    fun openCarEvent(carId: String) {
        _openCarMapEvent.value = Event(carId)
    }
}