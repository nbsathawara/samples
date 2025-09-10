package com.nbs.mywishlistapp.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.nbs.mywishlistapp.data.Constants
import com.nbs.mywishlistapp.data.models.Wish
import kotlinx.coroutines.flow.Flow
@Dao
interface WishDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWish(wish: Wish)

    @Update
    suspend fun updateWish(wish: Wish)

    @Delete
    suspend fun deleteWish(item: Wish)

    @Query("SELECT * FROM ${Constants.TABLE_WISH} WHERE id = :id")
    fun getWish(id: Long): Flow<Wish>

    @Query("SELECT * FROM ${Constants.TABLE_WISH}")
    fun getAllWish(): Flow<List<Wish>>

}