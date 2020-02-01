package com.hiroozawa.ractsil.data.mapper

import com.hiroozawa.ractsil.domain.FuelType
import org.hamcrest.core.IsEqual
import org.junit.Assert.assertThat
import org.junit.Test

class FuelTypeDataMapperTest {

    @Test
    fun `invoke should map D to FuelType Diesel`() {
        //given
        val fuelTypeString = "D"

        //when
        val fuelType = FuelTypeDataMapper(fuelTypeString)

        //then
        assertThat(fuelType, IsEqual(FuelType.DIESEL))
    }

    @Test
    fun `invoke should map P to FuelType Petroleum`() {
        //given
        val fuelTypeString = "P"

        //when
        val fuelType = FuelTypeDataMapper(fuelTypeString)

        //then
        assertThat(fuelType, IsEqual(FuelType.PETROLEUM))
    }

    @Test
    fun `invoke should map X to FuelType UNKNOWN`() {
        //given
        val fuelTypeString = "X"

        //when
        val fuelType = FuelTypeDataMapper(fuelTypeString)

        //then
        assertThat(fuelType, IsEqual(FuelType.UNKNOWN))
    }
}