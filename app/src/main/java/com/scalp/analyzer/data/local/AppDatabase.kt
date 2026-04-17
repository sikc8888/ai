package com.scalp.analyzer.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.scalp.analyzer.data.local.dao.CustomerDao
import com.scalp.analyzer.data.local.dao.DetectionRecordDao
import com.scalp.analyzer.data.local.entity.Customer
import com.scalp.analyzer.data.local.entity.DetectionRecord

@Database(
    entities = [Customer::class, DetectionRecord::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun customerDao(): CustomerDao
    abstract fun detectionRecordDao(): DetectionRecordDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "scalp_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
