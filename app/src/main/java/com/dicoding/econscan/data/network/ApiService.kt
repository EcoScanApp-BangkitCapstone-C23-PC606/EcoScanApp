package com.dicoding.econscan.data.network

import com.dicoding.econscan.data.response.PostSampahResponse
import okhttp3.MultipartBody
import retrofit2.http.*

interface ApiService {
@Multipart
@POST("classify")
suspend fun postSampah(
    @Part file: MultipartBody.Part,
    ): PostSampahResponse
}