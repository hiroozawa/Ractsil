package com.hiroozawa.ractsil.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hiroozawa.ractsil.data.CarRepository
import com.hiroozawa.ractsil.data.Result
import com.hiroozawa.ractsil.ui.model.CarUiModel
import com.hiroozawa.ractsil.ui.model.mappers.CarUiModelMapper
import com.hiroozawa.ractsil.ui.util.wrapEspressoIdlingResource
import kotlinx.coroutines.launch
import javax.inject.Inject

class CarDetailViewModel @Inject constructor(
    private val carRepository: CarRepository
) : ViewModel() {

    private val _carsUiModel = MutableLiveData<CarUiModel>().apply {
        value = CarUiModel()
    }
    val car: LiveData<CarUiModel> = _carsUiModel

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    fun load(carId: String) {
        wrapEspressoIdlingResource {

            viewModelScope.launch {
                when (val result = carRepository.getCar(carId)) {
                    is Result.Success -> _carsUiModel.value =
                        CarUiModelMapper(
                            result.data
                        )
                    is Result.Error -> _error.value = true
                }
            }

        }
    }
}