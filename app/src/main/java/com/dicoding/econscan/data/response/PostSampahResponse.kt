package com.dicoding.econscan.data.response

import com.google.gson.annotations.SerializedName

data class PostSampahResponse (
    @SerializedName("error")
    val error: Boolean,

    @SerializedName("message")
    val message: String,

    @SerializedName("class")
    val className: String
    )

