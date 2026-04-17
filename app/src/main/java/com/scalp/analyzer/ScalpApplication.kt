package com.scalp.analyzer

import android.app.Application
import com.scalp.analyzer.data.local.AppDatabase
import com.scalp.analyzer.security.ActivationManager
import com.scalp.analyzer.security.TamperFuse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ScalpApplication : Application() {
    lateinit var database: AppDatabase

    companion object {
        lateinit var instance: ScalpApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = AppDatabase.getInstance(this)
        ActivationManager.init(this)
        CoroutineScope(Dispatchers.IO).launch {
            TamperFuse.performSecurityCheck(this@ScalpApplication)
        }
    }
}
