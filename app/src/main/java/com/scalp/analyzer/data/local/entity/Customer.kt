package com.scalp.analyzer.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customers")
data class Customer(
    @PrimaryKey
    val id: String = java.util.UUID.randomUUID().toString(),
    val name: String,
    val phone: String,
    val age: Int?,
    val createdAt: Long = System.currentTimeMillis(),
    val lastDetectionDate: Long = 0
)
