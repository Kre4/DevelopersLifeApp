package com.kre4.androidtinkoff.retrofit

import com.google.gson.annotations.SerializedName

data class AnswerResult(
    @SerializedName("gifURL")
    private var url: String,
    @SerializedName("description")
    private val description: String
) {
    fun getUrl(): String = url
    fun getDescription(): String = description
}