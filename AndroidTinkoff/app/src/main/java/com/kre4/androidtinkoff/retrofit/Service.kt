package com.kre4.androidtinkoff.retrofit


import com.kre4.androidtinkoff.GlobalConstants
import retrofit2.Call
import retrofit2.http.GET



interface Service {
    @GET(GlobalConstants.pathHttpGet)
    fun getGifs(): Call<AnswerResult>
}