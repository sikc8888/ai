package com.scalp.analyzer.data.local.dao

import androidx.room.*
import com.scalp.analyzer.data.local.entity.Customer
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomerDao {
    @Query("SELECT * FROM customers ORDER BY lastDetectionDate DESC")
    fun getAllCustomers(): Flow<List<Customer>>

    @Query("SELECT * FROM customers WHERE name LIKE :query OR phone LIKE :query")
    fun searchCustomers(query: String): Flow<List<Customer>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(customer: Customer)

    @Query("UPDATE customers SET lastDetectionDate = :timestamp WHERE id = :customerId")
    suspend fun updateLastDetectionTime(customerId: String, timestamp: Long)
}
