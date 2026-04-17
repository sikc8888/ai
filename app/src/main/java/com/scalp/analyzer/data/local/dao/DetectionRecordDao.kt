package com.scalp.analyzer.data.local.dao

import androidx.room.*
import com.scalp.analyzer.data.local.entity.DetectionRecord
import kotlinx.coroutines.flow.Flow

@Dao
interface DetectionRecordDao {
    @Query("SELECT * FROM detection_records WHERE customerId = :customerId ORDER BY timestamp DESC LIMIT 1")
    suspend fun getLastDetection(customerId: String): DetectionRecord?

    @Query("SELECT * FROM detection_records WHERE id = :recordId")
    suspend fun getById(recordId: String): DetectionRecord?

    @Insert
    suspend fun insert(record: DetectionRecord)
}
