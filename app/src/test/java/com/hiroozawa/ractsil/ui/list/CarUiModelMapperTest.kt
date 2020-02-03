package com.hiroozawa.ractsil.ui.list

import androidx.test.espresso.matcher.ViewMatchers.assertThat
import com.hiroozawa.ractsil.R
import com.hiroozawa.ractsil.domain.*
import com.hiroozawa.ractsil.ui.model.CarUiModelMapper
import org.hamcrest.core.IsEqual
import org.junit.Test

class CarUiModelMapperTest {

    @Test
    fun `invoke should map CarId into CarUiModel id`() {
        //given
        val id = "123"
        val car = Car(carId = CarId(id))

        //when
        val carUiModel = CarUiModelMapper(car)

        //then
        assertThat(carUiModel.id, IsEqual(id))
    }

    @Test
    fun `invoke should map Owner into CarUiModel ownerName`() {
        //given
        val ownerName = "Hiro"
        val car = Car(owner = Owner(ownerName))

        //when
        val carUiModel = CarUiModelMapper(car)

        //then
        assertThat(carUiModel.ownerName, IsEqual(ownerName))
    }

    @Test
    fun `invoke should map CarImage into CarUiModel imageUrl`() {
        //given
        val carImageUrl = "http://someurl.com/car.png"
        val car = Car(carImage = CarImage(carImageUrl))

        //when
        val carUiModel = CarUiModelMapper(car)

        //then
        assertThat(carUiModel.imageUrl, IsEqual(carImageUrl))
    }

    @Test
    fun `invoke should map ModelName into CarUiModel modelName`() {
        //given
        val modelName = "MINI"
        val car = Car(model = Model(modelName = modelName))

        //when
        val carUiModel = CarUiModelMapper(car)

        //then
        assertThat(carUiModel.modelName, IsEqual(modelName))
    }

    @Test
    fun `invoke should map Make into CarUiModel makeName`() {
        //given
        val makeName = "BMW"
        val car = Car(make = Make(makeName))

        //when
        val carUiModel = CarUiModelMapper(car)

        //then
        assertThat(carUiModel.makeName, IsEqual(makeName))
    }

    @Test
    fun `invoke should map LicencePlate code into CarUiModel licensePlate`() {
        //given
        val licensePlate = "M-IL12893"
        val car = Car(licensePlate = LicencePlate(licensePlate))

        //when
        val carUiModel = CarUiModelMapper(car)

        //then
        assertThat(carUiModel.licensePlate, IsEqual(licensePlate))
    }

    @Test
    fun `invoke should map fuelLevel into fuelLevel percentage`() {
        //given
        val fuelLevel = 0.80F
        val car = Car(fuel = CarFuel(fuelLevel = fuelLevel))

        //when
        val carUiModel = CarUiModelMapper(car)

        //then
        assertThat(carUiModel.fuelLevel, IsEqual("80%"))
    }

    @Test
    fun `invoke should map fuelType Petrol into its StringRes id`() {
        //given
        val car = Car(fuel = CarFuel(fuelType = FuelType.PETROL))

        //when
        val carUiModel = CarUiModelMapper(car)

        //then
        assertThat(carUiModel.fuelType, IsEqual(R.string.petrol))
    }

    @Test
    fun `invoke should map fuelType Diesel into its StringRes id`() {
        //given
        val car = Car(fuel = CarFuel(fuelType = FuelType.DIESEL))

        //when
        val carUiModel = CarUiModelMapper(car)

        //then
        assertThat(carUiModel.fuelType, IsEqual(R.string.diesel))
    }

    @Test
    fun `invoke should map fuelType unknown into its StringRes id`() {
        //given
        val car = Car(fuel = CarFuel(fuelType = FuelType.UNKNOWN))

        //when
        val carUiModel = CarUiModelMapper(car)

        //then
        assertThat(carUiModel.fuelType, IsEqual(R.string.unknown))
    }

    @Test
    fun `invoke should map manual transmission into its StringRes id`() {
        //given
        val car = Car(transmission = Transmission.MANUAL)

        //when
        val carUiModel = CarUiModelMapper(car)

        //then
        assertThat(carUiModel.transmission, IsEqual(R.string.manual))
    }

    @Test
    fun `invoke should map auto transmission into its StringRes id`() {
        //given
        val car = Car(transmission = Transmission.AUTO)

        //when
        val carUiModel = CarUiModelMapper(car)

        //then
        assertThat(carUiModel.transmission, IsEqual(R.string.auto))
    }

    @Test
    fun `invoke should map unknown transmission into its StringRes id`() {
        //given
        val car = Car(transmission = Transmission.UNKNOWN)

        //when
        val carUiModel = CarUiModelMapper(car)

        //then
        assertThat(carUiModel.transmission, IsEqual(R.string.unknown))
    }

    @Test
    fun `invoke should map regular innerCleanliness into its StringRes id`() {
        //given
        val car = Car(innerCleanliness = InnerCleanliness.REGULAR)

        //when
        val carUiModel = CarUiModelMapper(car)

        //then
        assertThat(carUiModel.innerCleanliness, IsEqual(R.string.regular))
    }

    @Test
    fun `invoke should map clean innerCleanliness into its StringRes id`() {
        //given
        val car = Car(innerCleanliness = InnerCleanliness.CLEAN)

        //when
        val carUiModel = CarUiModelMapper(car)

        //then
        assertThat(carUiModel.innerCleanliness, IsEqual(R.string.clean))
    }

    @Test
    fun `invoke should map very clean innerCleanliness into its StringRes id`() {
        //given
        val car = Car(innerCleanliness = InnerCleanliness.VERY_CLEAN)

        //when
        //when
        val carUiModel = CarUiModelMapper(car)

        //then
        assertThat(carUiModel.innerCleanliness, IsEqual(R.string.very_clean))
    }

    @Test
    fun `invoke should map unknown innerCleanliness into its StringRes id`() {
        //given
        val car = Car(innerCleanliness = InnerCleanliness.UNKNOWN)

        //when
        //when
        val carUiModel = CarUiModelMapper(car)

        //then
        assertThat(carUiModel.innerCleanliness, IsEqual(R.string.unknown))
    }
}