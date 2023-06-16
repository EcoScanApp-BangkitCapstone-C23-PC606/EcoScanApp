package com.dicoding.econscan.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.econscan.data.network.ApiConfig
import com.dicoding.econscan.data.repository.SampahRepository
import com.dicoding.econscan.ui.scanner.UploadViewModel

class ViewModelFactory (private val context: Context) : ViewModelProvider.Factory {
    private fun providesRepository(): SampahRepository {
        val apiService = ApiConfig.getApiService(context)
        return SampahRepository(apiService)
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {

            modelClass.isAssignableFrom(UploadViewModel::class.java) -> {
                UploadViewModel(providesRepository()) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}