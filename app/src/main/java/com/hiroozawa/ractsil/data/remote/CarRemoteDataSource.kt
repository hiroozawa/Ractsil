package com.hiroozawa.ractsil.data.remote

import retrofit2.http.GET

interface CarRemoteDataSource {
    @GET("/codingtask/cars")
    suspend fun fetchCars(): List<CarResponse>
}

