package com.scalp.analyzer.simulator

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import kotlinx.coroutines.delay

class ScalpDetectorSimulator {
    enum class LightMode { WHITE, POLARIZED, UV }

    var isConnected = false

    fun connect(): Boolean {
        isConnected = true
        return true
    }

    fun setLightMode(mode: LightMode) {}

    suspend fun captureImage(): Bitmap {
        delay(300)
        return createSimulatedImage()
    }

    private fun createSimulatedImage(): Bitmap {
        val bitmap = Bitmap.createBitmap(640, 480, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        canvas.drawColor(Color.parseColor("#E0C9A6"))
        val paint = Paint().apply { color = Color.BLACK; strokeWidth = 3f }
        for (i in 0..10) {
            val x = (Math.random() * 640).toFloat()
            val y = (Math.random() * 480).toFloat()
            canvas.drawCircle(x, y, 10f, paint)
        }
        return bitmap
    }
}
