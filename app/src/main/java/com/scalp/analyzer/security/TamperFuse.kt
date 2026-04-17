package com.scalp.analyzer.security

import android.content.Context
import android.os.Build
import android.util.Log
import java.io.File

object TamperFuse {
    enum class ThreatLevel { SAFE, SUSPICIOUS, HIGH_RISK, CRITICAL }

    suspend fun performSecurityCheck(context: Context): ThreatLevel {
        if (isDeviceRooted()) return ThreatLevel.HIGH_RISK
        if (isHookFrameworkDetected()) return ThreatLevel.HIGH_RISK
        return ThreatLevel.SAFE
    }

    private fun isDeviceRooted(): Boolean {
        val paths = arrayOf("/system/app/Superuser.apk", "/sbin/su", "/system/bin/su", "/system/xbin/su")
        return paths.any { File(it).exists() }
    }

    private fun isHookFrameworkDetected(): Boolean {
        return try {
            File("/data/data/de.robv.android.xposed.installer").exists()
        } catch (e: Exception) {
            false
        }
    }
}
