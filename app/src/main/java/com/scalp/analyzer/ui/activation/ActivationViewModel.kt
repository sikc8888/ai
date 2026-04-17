package com.scalp.analyzer.ui.activation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scalp.analyzer.security.ActivationManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ActivationViewModel : ViewModel() {
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    suspend fun activate(context: Context, licenseKey: String): Boolean {
        _isLoading.value = true
        _errorMessage.value = null
        val result = ActivationManager.activate(context, licenseKey)
        _isLoading.value = false
        result.fold(onSuccess = { return true }, onFailure = { _errorMessage.value = it.message })
        return false
    }
}
