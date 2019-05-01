package com.volkovmedia.core.data.datasource.database.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(item: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(items: List<T>): List<Long>

    @Delete
    fun remove(item: T)

}