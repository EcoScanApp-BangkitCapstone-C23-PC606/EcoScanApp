package com.dicoding.econscan.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.dicoding.econscan.data.network.ApiService
import com.dicoding.econscan.data.response.PostSampahResponse
import okhttp3.MultipartBody
import com.dicoding.econscan.data.util.Result

class SampahRepository(private val apiService: ApiService) {
    fun postSampah (file: MultipartBody.Part): LiveData<Result<PostSampahResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.postSampah(file)
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.e("UploadViewModel", "postSampah: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }
}