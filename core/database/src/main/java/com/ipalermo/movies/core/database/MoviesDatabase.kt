package com.ipalermo.movies.core.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ipalermo.movies.core.database.dao.MovieKeysDao
import com.ipalermo.movies.core.database.dao.MovieResourceDao
import com.ipalermo.movies.core.database.model.MovieKeysEntity
import com.ipalermo.movies.core.database.model.MovieResourceEntity
import com.ipalermo.movies.core.database.util.LocalDateConverter

@Database(
    entities = [
        MovieResourceEntity::class,
        MovieKeysEntity::class
    ],
    version = 2,
    autoMigrations = [
        AutoMigration(from = 1, to = 2, spec = DatabaseMigrations.Schema1to2::class)
    ],
    exportSchema = true,
)
@TypeConverters(
    LocalDateConverter::class
)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun movieResourceDao(): MovieResourceDao
    abstract fun movieKeysDao(): MovieKeysDao
}
