package com.hiroozawa.ractsil.data.mapper

import com.hiroozawa.ractsil.domain.Transmission
import org.hamcrest.core.IsEqual
import org.junit.Assert.assertThat
import org.junit.Test

class TransmissionDataMapperTest {

    @Test
    fun `invoke should map M to Transmission MANUAL`() {
        //given
        val transmissionString = "M"

        //when
        val transmission = TransmissionDataMapper(transmissionString)

        //then
        assertThat(transmission, IsEqual(Transmission.MANUAL))
    }

    @Test
    fun `invoke should map A to Transmission AUTOMATIC`() {
        //given
        val transmissionString = "A"

        //when
        val transmission = TransmissionDataMapper(transmissionString)

        //then
        assertThat(transmission, IsEqual(Transmission.AUTO))
    }

    @Test
    fun `invoke should map X to Transmission UNKNOWN`() {
        //given
        val transmissionString = "X"

        //when
        val transmission = TransmissionDataMapper(transmissionString)

        //then
        assertThat(transmission, IsEqual(Transmission.UNKNOWN))
    }
}