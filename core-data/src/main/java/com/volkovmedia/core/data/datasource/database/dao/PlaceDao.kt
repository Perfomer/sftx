package com.volkovmedia.core.data.datasource.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.volkovmedia.core.data.datasource.database.entity.Place
import io.reactivex.Observable
import io.reactivex.Single

@Dao
internal interface PlaceDao : BaseDao<Place> {

    @Query("SELECT * FROM Place WHERE name LIKE :filterQuery ORDER BY time")
    fun getPlaces(filterQuery: String): Observable<List<Place>>

    @Query("SELECT * FROM Place WHERE id = :id")
    fun getPlace(id: String): Single<Place>

}