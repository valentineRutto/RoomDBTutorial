package com.valentinerutto.roomdbtutorial.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

//data access object , persists changes to db , gets all entities from db
@Dao
interface PickupLineDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entity: List<PickuplineEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: List<PickuplineEntity>)

    @Query("SELECT * FROM pickuplines")
    fun getAllLines(): Flow<List<PickuplineEntity>>

    @Query("Delete FROM pickuplines where idKey =:idKey")
    suspend fun deleteLine(idKey: Int)

    @Query("Delete  FROM pickuplines ")
    suspend fun deleteAll()
}