package com.ipalermo.movies.core.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.ipalermo.movies.core.database.model.MovieResourceEntity
import com.ipalermo.movies.core.model.data.MovieResource
import kotlinx.coroutines.flow.Flow

/**
 * DAO for [MovieResource] and [MovieResourceEntity] access
 */
@Dao
interface MovieResourceDao {
    @Transaction
    @Query(
        value = """
            SELECT * FROM movie_resources
    """
    )
    fun getMovieResourcesPagingSource(): PagingSource<Int, MovieResourceEntity>

    @Transaction
    @Query(
        value = """
            SELECT * FROM movie_resources
    """
    )
    fun getMovieResourcesStream(): Flow<List<MovieResourceEntity>>

    @Query(
        value = """
        SELECT * FROM movie_resources
        WHERE movie_id = :movieId
    """
    )
    fun getMovieResourceEntityStream(movieId: Long): Flow<MovieResourceEntity>

    /**
     * Inserts [entities] into the db if they don't exist, and replaces those that do
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entities: List<MovieResourceEntity>): List<Long>

    /**
     * Deletes all rows in movie_resources table
     */
    @Query(
        value = """
            DELETE FROM movie_resources
        """
    )
    suspend fun deleteMovieResources()

}
