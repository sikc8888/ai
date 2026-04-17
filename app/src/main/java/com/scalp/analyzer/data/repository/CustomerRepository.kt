package com.scalp.analyzer.data.repository

import com.scalp.analyzer.ScalpApplication
import com.scalp.analyzer.data.local.AppDatabase
import com.scalp.analyzer.data.local.entity.Customer
import kotlinx.coroutines.flow.Flow

class CustomerRepository(
    private val db: AppDatabase = ScalpApplication.instance.database
) {
    fun getAllCustomers(): Flow<List<Customer>> = db.customerDao().getAllCustomers()
    fun searchCustomers(query: String): Flow<List<Customer>> = db.customerDao().searchCustomers("%$query%")
    suspend fun insertCustomer(customer: Customer) = db.customerDao().insert(customer)
    suspend fun updateLastDetectionTime(customerId: String, timestamp: Long) = db.customerDao().updateLastDetectionTime(customerId, timestamp)
}
