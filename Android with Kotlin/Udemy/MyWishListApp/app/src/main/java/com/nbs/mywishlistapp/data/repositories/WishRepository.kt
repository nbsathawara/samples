package com.nbs.mywishlistapp.data.repositories

import com.nbs.mywishlistapp.database.dao.WishDao
import com.nbs.mywishlistapp.data.models.Wish
import kotlinx.coroutines.flow.Flow

class WishRepository(private val wishDao: WishDao) {

    suspend fun insertWish(wish: Wish) = wishDao.insertWish(wish)
    suspend fun updateWish(wish: Wish) = wishDao.updateWish(wish)
    suspend fun deleteWish(wish: Wish) = wishDao.deleteWish(wish)
    fun getWish(id: Long): Flow<Wish> = wishDao.getWish(id)
    fun getAllWish(): Flow<List<Wish>> = wishDao.getAllWish()

}