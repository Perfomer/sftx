package com.volkovmedia.core.data

import com.volkovmedia.core.data.datasource.database.dao.PlaceDao
import com.volkovmedia.core.data.datasource.database.entity.Place
import com.volkovmedia.core.data.datasource.network.SftxApi
import com.volkovmedia.core.data.repository.PlaceProvider
import io.mockk.*
import io.reactivex.Observable
import org.junit.Test

class PlaceRepositoryTest {

    private val placeDao = mockk<PlaceDao>()

    private val api = mockk<SftxApi>()

    private val repository = PlaceProvider(placeDao, api)

    @Test
    fun wrap_percent_search_query() {
        val query = "query 123124112"

        every { placeDao.getPlaces(any()) } returns Observable.empty<List<Place>>()

        repository.getPlaces(query)

        verify { placeDao.getPlaces("%$query%") }
    }

}