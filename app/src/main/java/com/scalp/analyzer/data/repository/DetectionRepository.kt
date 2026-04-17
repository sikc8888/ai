package com.scalp.analyzer.data.repository

import android.graphics.Bitmap
import com.google.gson.Gson
import com.scalp.analyzer.ScalpApplication
import com.scalp.analyzer.data.local.AppDatabase
import com.scalp.analyzer.data.local.entity.DetectionRecord
import com.scalp.analyzer.simulator.AIAnalyzerSimulator
import java.io.File
import java.io.FileOutputStream

class DetectionRepository(
    private val db: AppDatabase = ScalpApplication.instance.database
) {
    suspend fun getLastDetection(customerId: String): DetectionRecord? = db.detectionRecordDao().getLastDetection(customerId)

    suspend fun getRecordById(recordId: String): DetectionRecord? = db.detectionRecordDao().getById(recordId)

    suspend fun createDetectionRecord(
        customerId: String,
        analysisResult: AIAnalyzerSimulator.AnalysisResult?,
        whiteBitmap: Bitmap?,
        polarizedBitmap: Bitmap?,
        uvBitmap: Bitmap?
    ): DetectionRecord {
        val timestamp = System.currentTimeMillis()
        val whitePath = whiteBitmap?.let { saveBitmap(it, "white_$timestamp.jpg") }
        val polarizedPath = polarizedBitmap?.let { saveBitmap(it, "polarized_$timestamp.jpg") }
        val uvPath = uvBitmap?.let { saveBitmap(it, "uv_$timestamp.jpg") }

        val record = DetectionRecord(
            customerId = customerId,
            timestamp = timestamp,
            whiteImagePath = whitePath,
            polarizedImagePath = polarizedPath,
            uvImagePath = uvPath,
            aiTags = analysisResult?.rawLabels ?: emptyList(),
            finalTags = analysisResult?.rawLabels ?: emptyList(),
            scalpAge = analysisResult?.scalpAge ?: 0,
            overallScore = analysisResult?.getOverallScore() ?: 0,
            scoresJson = Gson().toJson(analysisResult?.scores ?: emptyMap<String, Any>())
        )
        db.detectionRecordDao().insert(record)
        return record
    }

    private fun saveBitmap(bitmap: Bitmap, fileName: String): String? {
        return try {
            val dir = ScalpApplication.instance.filesDir
            val file = File(dir, fileName)
            FileOutputStream(file).use { out ->
                bitmap.compress(Bitmap.CompressFormat.JPEG, 85, out)
            }
            file.absolutePath
        } catch (e: Exception) {
            null
        }
    }
}
