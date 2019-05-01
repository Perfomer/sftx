package com.volkovmedia.core.data.repository

import com.volkovmedia.core.data.datasource.database.entity.Place
import com.volkovmedia.core.data.datasource.network.model.PlaceModel
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Repository for the [Place] entity
 */
interface PlaceRepository {

    /**
     * Get [Place]s list Observable from local database
     *
     * @param filterQuery filter query for search by name of the [Place]
     */
    fun getPlaces(filterQuery: String): Observable<List<Place>>

    /**
     * Get [Place] Single
     *
     * @param id place identifier
     */
    fun getPlace(id: String): Single<Place>

    /**
     * Insert [Place]
     *
     * @param place place entity for inserting
     */
    fun insertPlace(place: Place): Completable

    /**
     * Remove [Place]
     *
     * @param place place entity for removing
     */
    fun removePlace(place: Place): Completable

    /**
     * Synchronize places with Network
     *
     * Automatically adds places to the local database
     */
    fun synchronizeWithNetwork(): Completable

}