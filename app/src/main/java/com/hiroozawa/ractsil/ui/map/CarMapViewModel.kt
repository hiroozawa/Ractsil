package com.hiroozawa.ractsil.ui.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLngBounds
import com.hiroozawa.ractsil.R
import com.hiroozawa.ractsil.data.CarRepository
import com.hiroozawa.ractsil.data.Result
import com.hiroozawa.ractsil.domain.Car
import com.hiroozawa.ractsil.ui.Event
import com.hiroozawa.ractsil.ui.model.CarMapUiState
import com.hiroozawa.ractsil.ui.model.mappers.CarMakersUiModelMapper
import com.hiroozawa.ractsil.ui.model.mappers.LatLngBoundsMapper
import com.hiroozawa.ractsil.ui.model.mappers.SingleLatLngBoundsMapper
import com.hiroozawa.ractsil.ui.util.wrapEspressoIdlingResource
import kotlinx.coroutines.launch
import javax.inject.Inject

class CarMapViewModel @Inject constructor(
    private val carRepository: CarRepository
) : ViewModel() {

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _errorEvent = MutableLiveData<Event<Int>>()
    val errorEvent: LiveData<Event<Int>> = _errorEvent

    private val _carMapUiModel = MutableLiveData<CarMapUiState>()
    val carMapState = _carMapUiModel

    fun load(selectedCarId: String? = null) {
        _dataLoading.value = true

        wrapEspressoIdlingResource {
            viewModelScope.launch {
                when (val result = carRepository.fetchCars()) {
                    is Result.Success -> _carMapUiModel.value =
                        mapCarsIntoUiState(result.data, selectedCarId)
                    is Result.Error -> _errorEvent.value = Event(R.string.error)
                }
                _dataLoading.value = false
            }
        }
    }

    private fun mapCarsIntoUiState(
        cars: List<Car>,
        selectedCarId: String?
    ): CarMapUiState =
        if (cars.isEmpty()) {
            CarMapUiState.Empty
        } else {
            CarMapUiState.MapUiData(
                latLngBounds = selectCameraUpdateMapper(selectedCarId, cars),
                carMarkers = CarMakersUiModelMapper(cars)
            )
        }

    private fun selectCameraUpdateMapper(
        selectedCarId: String?,
        cars: List<Car>
    ): LatLngBounds {
        return if (selectedCarId == null) {
            LatLngBoundsMapper(cars)
        } else {
            SingleLatLngBoundsMapper(cars, selectedCarId)
        }
    }

}