package com.volkovmedia.core.data.datasource.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.volkovmedia.core.data.datasource.database.dao.PlaceDao
import com.volkovmedia.core.data.datasource.database.converter.DateConverter
import com.volkovmedia.core.data.datasource.database.entity.Place

@Database(
    version = 1,
    exportSchema = true,
    entities = [Place::class]
)
@TypeConverters(DateConverter::class)
internal abstract class SftxDatabase : RoomDatabase() {

    abstract fun getPlaceDao(): PlaceDao

    companion object {

        @Synchronized
        fun getInstance(
            appContext: Context,
            databaseName: String,
            inMemory: Boolean = false
        ): SftxDatabase {
            return if (inMemory) {
                Room.inMemoryDatabaseBuilder(appContext, SftxDatabase::class.java).build()
            } else {
                Room.databaseBuilder(appContext, SftxDatabase::class.java, databaseName)
                    .fallbackToDestructiveMigration()
                    .build()
            }
        }

    }

}