package com.valentinerutto.roomdbtutorial.data.local

import androidx.lifecycle.LiveData
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
    suspend fun insert(entity: PickuplineEntity)

    @Query("SELECT * FROM pickuplines")
    suspend fun getAllLines(): Flow<List<PickuplineEntity>>

    @Query("Delete FROM pickuplines where id = id")
    suspend fun deleteLine(id: String)

}