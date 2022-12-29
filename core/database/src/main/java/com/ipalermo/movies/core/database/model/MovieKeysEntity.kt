package com.ipalermo.movies.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_keys")
data class MovieKeysEntity(
    @PrimaryKey
    @ColumnInfo(name = "movie_id")
    val movieId: Long,
    val prevKey: Int?,
    val nextKey: Int?
)
