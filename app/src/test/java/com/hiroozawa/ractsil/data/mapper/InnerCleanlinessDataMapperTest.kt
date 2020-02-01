package com.hiroozawa.ractsil.data.mapper

import com.hiroozawa.ractsil.domain.InnerCleanliness
import org.hamcrest.core.IsEqual
import org.junit.Assert.assertThat
import org.junit.Test

class InnerCleanlinessDataMapperTest {
    
    @Test
    fun `invoke should map REGULAR into Cleanliness REGULAR`() {
        //given
        val cleanlinessString = "REGULAR"

        //when
        val cleanliness = CleanlinessDataMapper(cleanlinessString)

        //then
        assertThat(cleanliness, IsEqual(InnerCleanliness.REGULAR))
    }

    @Test
    fun `invoke should map CLEAN into Cleanliness CLEAN`() {
        //given
        val cleanlinessString = "CLEAN"

        //when
        val cleanliness = CleanlinessDataMapper(cleanlinessString)

        //then
        assertThat(cleanliness, IsEqual(InnerCleanliness.CLEAN))
    }

    @Test
    fun `invoke should map VERY_CLEAN into Cleanliness VERY_CLEAN`() {
        //given
        val cleanlinessString = "VERY_CLEAN"

        //when
        val cleanliness = CleanlinessDataMapper(cleanlinessString)

        //then
        assertThat(cleanliness, IsEqual(InnerCleanliness.VERY_CLEAN))
    }

    @Test
    fun `invoke should map X into Cleanliness UNKNOWN`() {
        //given
        val cleanlinessString = "X"

        //when
        val cleanliness = CleanlinessDataMapper(cleanlinessString)

        //then
        assertThat(cleanliness, IsEqual(InnerCleanliness.UNKNOWN))
    }
}