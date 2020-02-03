package com.hiroozawa.ractsil.data

import com.hiroozawa.ractsil.data.remote.CarRemoteDataSource
import com.hiroozawa.ractsil.data.remote.CarResponse

class FakeCarRemoteDataSource(
    val responseList: MutableList<CarResponse>? = createFakeCarData()
) : CarRemoteDataSource {

    override suspend fun fetchCars(): List<CarResponse> {
        if (responseList == null) throw Exception("Test Network error")
        return responseList
    }
}

private fun createFakeCarData(): MutableList<CarResponse> =
    listOf(0, 1, 2, 3)
        .map {
            CarResponse(
                id = "$it",
                name = "Car $it"
            )
        }.toMutableList()
