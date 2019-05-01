package com.volkovmedia.core.data.datasource

import android.content.Context
import androidx.room.EmptyResultSetException
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.volkovmedia.core.common.util.currentDate
import com.volkovmedia.core.data.datasource.database.SftxDatabase
import com.volkovmedia.core.data.datasource.database.dao.PlaceDao
import com.volkovmedia.core.data.datasource.database.entity.Place
import io.reactivex.rxkotlin.flatMapIterable
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class PlaceDaoTest {

    private lateinit var database: SftxDatabase

    private lateinit var dao: PlaceDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = SftxDatabase.getInstance(context, "test.db", true)
        dao = database.getPlaceDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }


    @Test
    fun get_by_id() {
        val id = "place_id"
        val place = Place(id = id, time = currentDate, name = "Place", imageUrl = null)

        dao.insert(place)

        dao.getPlace(id).test().assertValue(place)
    }

    @Test
    fun get_all() {
        val places = listOf(
            Place(id = "99", time = currentDate, name = "Place 1", imageUrl = null),
            Place(id = "101", time = currentDate, name = "Place 2", imageUrl = null),
            Place(id = "102", time = currentDate, name = "Place 3", imageUrl = null),
            Place(id = "103", time = currentDate, name = "Place 4", imageUrl = null)
        )

        dao.insert(places)

        dao.getPlaces("%%")
            .flatMapIterable()
            .test()
            .assertValueSet(places)
    }

    @Test
    fun search() {
        val places = listOf(
            Place(id = "99", time = currentDate, name = "Place 1", imageUrl = null),
            Place(id = "101", time = currentDate, name = "Place 2", imageUrl = null),
            Place(id = "102", time = currentDate, name = "Place 3", imageUrl = null),
            Place(id = "103", time = currentDate, name = "Place 4", imageUrl = null)
        )

        dao.insert(places)

        assertEquals(places[0], dao.getPlaces("%${places[0].name}%").blockingFirst()[0])
    }

    @Test
    fun remove() {
        val id = "place_id"
        val place = Place(id = id, time = currentDate, name = "Place", imageUrl = null)

        dao.insert(place)

        dao.getPlace(id).test().assertValue(place)

        dao.remove(place)

        dao.getPlace(id).test().assertError(EmptyResultSetException::class.java)
    }

}