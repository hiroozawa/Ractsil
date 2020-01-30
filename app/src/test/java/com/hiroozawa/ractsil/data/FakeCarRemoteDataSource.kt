package com.hiroozawa.ractsil.data

import com.hiroozawa.ractsil.data.remote.CarRemoteDataSource
import com.hiroozawa.ractsil.data.remote.CarResponse

class FakeCarRemoteDataSource(
    private val responseList: List<CarResponse>? = createFakeCarData()
) : CarRemoteDataSource {

    override suspend fun fetchCars(): List<CarResponse> {
        if (responseList == null) throw Exception("Some Network error")
        return responseList
    }
}

private fun createFakeCarData(): List<CarResponse>? =
    listOf(0, 1, 2, 3)
        .map {
            CarResponse(
                id = "$it",
                name = "Car $it"
            )
        }
