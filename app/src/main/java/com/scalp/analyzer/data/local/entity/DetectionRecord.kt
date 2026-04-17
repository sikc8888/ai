package com.scalp.analyzer.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "detection_records")
data class DetectionRecord(
    @PrimaryKey
    val id: String = java.util.UUID.randomUUID().toString(),
    val customerId: String,
    val timestamp: Long,
    val whiteImagePath: String?,
    val polarizedImagePath: String?,
    val uvImagePath: String?,
    val aiTags: List<String>,
    val finalTags: List<String>,
    val scalpAge: Int,
    val overallScore: Int,
    val scoresJson: String,
    val notes: String = ""
)
