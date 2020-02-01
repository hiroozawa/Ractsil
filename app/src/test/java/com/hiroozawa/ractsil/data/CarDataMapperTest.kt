package com.hiroozawa.ractsil.data

import com.hiroozawa.ractsil.data.mapper.CarDataMapper
import com.hiroozawa.ractsil.data.remote.CarResponse
import com.hiroozawa.ractsil.domain.InnerCleanliness
import com.hiroozawa.ractsil.domain.Transmission
import org.hamcrest.core.IsEqual
import org.junit.Assert.assertThat
import org.junit.Test

class CarDataMapperTest() {

    @Test
    fun `invoke should map id into CarId`() {
        //given
        val carId = "abc1234"
        val carResponse = CarResponse(id = carId)

        //when
        val car = CarDataMapper(carResponse.toList()).first()

        //then
        assertThat(car.carId.id, IsEqual(carId))
    }


    @Test
    fun `invoke should map name into Owner`() {
        //given
        val name = "Hiro"
        val carResponse = CarResponse(name = name)

        //when
        val car = CarDataMapper(carResponse.toList()).first()

        //then
        assertThat(car.owner.name, IsEqual(name))
    }

    @Test
    fun `invoke should map latitude and longitude into Coordinate`() {
        //given
        val latitude = 123.123
        val longitude = 321.321

        val carResponse = CarResponse(latitude = latitude, longitude = longitude)

        //when
        val car = CarDataMapper(carResponse.toList()).first()

        //then
        assertThat(latitude, IsEqual(car.coordinate.latitude))
        assertThat(longitude, IsEqual(car.coordinate.longitude))
    }

    @Test
    fun `invoke should map modelId and model into Model`() {
        //given
        val identifier = "mini_cabrio"
        val name = "MINI CABRIO"

        val carResponse = CarResponse(modelName = name, modelIdentifier = identifier)

        //when
        val car = CarDataMapper(carResponse.toList()).first()

        //then
        assertThat(identifier, IsEqual(car.model.modelId))
        assertThat(name, IsEqual(car.model.modelName))
    }

    @Test
    fun `invoke should map make into Make`() {
        //given
        val name = "BMW"

        val carResponse = CarResponse(make = name)

        //when
        val car = CarDataMapper(carResponse.toList()).first()

        //then
        assertThat(name, IsEqual(car.make.name))
    }

    @Test
    fun `invoke should map fuelLevel and fuelType into CarFuel`() {
        //given
        val fuelLevel = 0.8F
        val fuelType = "D"

        val carResponse = CarResponse(fuelLevel = fuelLevel, fuelType = fuelType)

        //when
        val car = CarDataMapper(carResponse.toList()).first()

        //then
        assertThat(fuelLevel, IsEqual(car.fuel.fuelLevel))
    }

    @Test
    fun `invoke should map transmission into Transmission`() {
        //given
        val transmission = "A"

        val carResponse = CarResponse(transmission = transmission)

        //when
        val car = CarDataMapper(carResponse.toList()).first()

        //then
        assertThat(Transmission.AUTOMATIC, IsEqual(car.transmission))
    }

    @Test
    fun `invoke should map licensePlate into LicensePlate code`() {
        //given
        val licensePlate = "M-IL2647"

        val carResponse = CarResponse(licensePlate = licensePlate)

        //when
        val car = CarDataMapper(carResponse.toList()).first()

        //then
        assertThat(licensePlate, IsEqual(car.licensePlate.code))
    }

    @Test
    fun `invoke should map innerCleanliness into InnerCleanliness`() {
        //given
        val cleanliness = "REGULAR"

        val carResponse = CarResponse(innerCleanliness = cleanliness)

        //when
        val car = CarDataMapper(carResponse.toList()).first()

        //then
        assertThat(InnerCleanliness.REGULAR, IsEqual(car.innerCleanliness))
    }

    @Test
    fun `invoke should map carImageUrl into CarImage url`() {
        //given
        val carImageUrl = "http://someurl.com/car"

        val carResponse = CarResponse(carImageUrl = carImageUrl)

        //when
        val car = CarDataMapper(carResponse.toList()).first()

        //then
        assertThat(carImageUrl, IsEqual(car.carImageUrl.url))
    }

    private fun CarResponse.toList() = listOf(this)

}