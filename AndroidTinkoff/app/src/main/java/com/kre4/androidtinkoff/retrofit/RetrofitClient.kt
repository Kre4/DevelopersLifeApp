package com.kre4.androidtinkoff.retrofit


import com.kre4.androidtinkoff.GlobalConstants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {
        val instance = RetrofitClient()
    }

    private var retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(GlobalConstants.baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val service: Service = retrofit.create(Service::class.java)


}