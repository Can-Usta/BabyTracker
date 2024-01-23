package com.okation.aivideocreator.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.okation.aivideocreator.data.dao.BabyTrackerDao
import com.okation.aivideocreator.data.entities.Feeding
import com.okation.aivideocreator.data.entities.Sleeping
import com.okation.aivideocreator.data.entities.Symptoms

@Database(entities = [Feeding::class, Sleeping::class,Symptoms::class], version = 6, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun babyTrackerDao(): BabyTrackerDao


    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "baby_tracker_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

}