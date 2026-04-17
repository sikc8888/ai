package com.scalp.analyzer.simulator

import android.graphics.Bitmap
import kotlinx.coroutines.delay
import kotlin.random.Random

object AIAnalyzerSimulator {
    data class TagConfidence(val tag: String, val confidence: Float)
    data class AnalysisResult(
        val tags: List<TagConfidence>,
        val scalpAge: Int,
        val scores: Map<String, Float>,
        val rawLabels: List<String>
    ) {
        fun getOverallScore(): Int = scores.values.average().toInt()
    }

    suspend fun analyze(
        whiteImage: Bitmap,
        polarizedImage: Bitmap?,
        uvImage: Bitmap?,
        mode: String
    ): AnalysisResult {
        delay(2000)
        val tags = listOf(
            TagConfidence("头发细软", 0.87f),
            TagConfidence("头油", 0.92f),
            TagConfidence("毛囊堵塞", 0.78f)
        )
        val scores = mapOf(
            "清洁度" to 65f,
            "油脂度" to 78f,
            "敏感度" to 30f,
            "毛囊健康度" to 55f,
            "发干强韧度" to 70f
        )
        return AnalysisResult(
            tags = tags,
            scalpAge = Random.nextInt(25, 50),
            scores = scores,
            rawLabels = tags.map { it.tag }
        )
    }
}
