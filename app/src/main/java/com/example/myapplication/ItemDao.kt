package com.example.myapplication

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao{
    @Query("SELECT * from item ORDER BY name ASC")
    fun getItems(): Flow<List<Item>>
    @Query("SELECT * from item WHERE id = :id")
    fun getItem(id: Int): Flow<Item>
    @Insert(onConflict = 5)
    suspend fun insert(item: Item)
    @Update
    suspend fun update(item: Item)
    @Delete
    suspend fun delete(item: Item)

}
