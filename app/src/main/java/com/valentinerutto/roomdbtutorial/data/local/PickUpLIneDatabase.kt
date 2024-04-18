package com.valentinerutto.roomdbtutorial.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.valentinerutto.roomdbtutorial.utils.Constants

@Database(
    entities = [PickuplineEntity::class], version = 1, exportSchema = false
)
abstract class PickUpLIneDatabase : RoomDatabase() {
    abstract fun lineDao(): PickupLineDao

    companion object {
        @Volatile
        private var INSTANCE: PickUpLIneDatabase? = null
        fun getDatabase(context: Context): PickUpLIneDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, PickUpLIneDatabase::class.java, Constants.DB_NAME
                ).allowMainThreadQueries()
                    // Wipes and rebuilds instead of migrating if no M¬igration object.
                    .fallbackToDestructiveMigration().build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}