package com.volkovmedia.core.data.datasource.network

import com.volkovmedia.core.data.datasource.network.model.PlaceModel
import io.reactivex.Single
import retrofit2.http.GET

internal interface SftxApi {

    @GET("test.json")
    fun getPlaces(): Single<List<PlaceModel>>

}