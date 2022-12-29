package com.ipalermo.movies.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ipalermo.movies.core.database.model.MovieKeysEntity

@Dao
interface MovieKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<MovieKeysEntity>)

    @Query("SELECT * FROM movie_keys WHERE movie_id = :movieId")
    suspend fun keysMovieId(movieId: Long): MovieKeysEntity?

    @Query("DELETE FROM movie_keys")
    suspend fun clearKeys()
}
