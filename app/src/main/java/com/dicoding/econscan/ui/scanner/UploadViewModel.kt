package com.dicoding.econscan.ui.scanner

import androidx.lifecycle.ViewModel
import com.dicoding.econscan.data.repository.SampahRepository
import okhttp3.MultipartBody


class UploadViewModel (private val sampahRepository: SampahRepository): ViewModel() {
    fun postSampah(file: MultipartBody.Part) = sampahRepository.postSampah(file)
}