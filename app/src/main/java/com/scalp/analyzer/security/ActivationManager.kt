package com.scalp.analyzer.security

import android.content.Context
import android.content.SharedPreferences
import android.provider.Settings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.security.MessageDigest

object ActivationManager {
    private const val PREFS_NAME = "scalp_secure_prefs"
    private const val KEY_ACTIVATED = "activated"
    private const val DEMO_LICENSE_KEY = "skco8888"

    private lateinit var prefs: SharedPreferences

    fun init(context: Context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun getDeviceFingerprint(context: Context): String {
        val androidId = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
        val serial = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            android.os.Build.getSerial()
        } else {
            android.os.Build.SERIAL
        }
        val raw = "$androidId-$serial-${context.packageName}"
        return MessageDigest.getInstance("SHA-256").digest(raw.toByteArray()).joinToString("") { "%02x".format(it) }
    }

    suspend fun activate(context: Context, licenseKey: String): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            if (licenseKey != DEMO_LICENSE_KEY) {
                return@withContext Result.failure(Exception("激活码无效，演示密钥为: $DEMO_LICENSE_KEY"))
            }
            prefs.edit().putBoolean(KEY_ACTIVATED, true).apply()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun isActivated(): Boolean = prefs.getBoolean(KEY_ACTIVATED, false)
    fun verifyRecoveryKey(inputKey: String): Boolean = inputKey == DEMO_LICENSE_KEY
}
