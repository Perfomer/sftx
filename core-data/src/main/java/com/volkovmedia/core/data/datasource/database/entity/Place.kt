package com.volkovmedia.core.data.datasource.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.volkovmedia.core.common.KeyEntity
import java.util.*

@Entity
data class Place(
    @PrimaryKey
    override val id: String,

    val time: Date,

    val name: String,

    val imageUrl: String?
) : KeyEntity<String>