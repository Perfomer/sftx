package com.volkovmedia.core.data.repository

import com.volkovmedia.core.common.util.parseDate
import com.volkovmedia.core.data.datasource.database.dao.PlaceDao
import com.volkovmedia.core.data.datasource.database.entity.Place
import com.volkovmedia.core.data.datasource.network.SftxApi
import com.volkovmedia.core.data.datasource.network.model.PlaceModel
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

internal class PlaceProvider(
    private val dao: PlaceDao,
    private val api: SftxApi
) : PlaceRepository {

    override fun getPlaces(filterQuery: String): Observable<List<Place>> {
        return dao.getPlaces("%$filterQuery%")
    }

    override fun getPlace(id: String): Single<Place> {
        return dao.getPlace(id)
    }

    override fun insertPlace(place: Place): Completable {
        return Completable.fromAction { dao.insert(place) }
    }

    override fun removePlace(place: Place): Completable {
        return Completable.fromAction { dao.remove(place) }
    }

    override fun synchronizeWithNetwork(): Completable {
        fun PlaceModel.toEntity() = Place(
            id = id,
            time = time.parseDate(TIME_FORMAT),
            name = name,
            imageUrl = imageUrl
        )

        return api.getPlaces()
            .map { models -> models.map { it.toEntity() } }
            .flatMapCompletable { Completable.fromAction { dao.insert(it) } }
    }

    private companion object {

        private const val TIME_FORMAT = "yyyy-MM-dd hh:mm:ss.SSSSSSS"

    }

}