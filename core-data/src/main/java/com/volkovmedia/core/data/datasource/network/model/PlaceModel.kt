package com.volkovmedia.core.data.datasource.network.model

import com.google.gson.annotations.SerializedName

internal data class PlaceModel(
    @SerializedName("Id")
    val id: String,

    @SerializedName("Time")
    val time: String,

    @SerializedName("Name")
    val name: String,

    @SerializedName("Image")
    val imageUrl: String?
)