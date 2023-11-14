package com.aliceresponde.aristiinstagramcompose.core.network

import com.aliceresponde.aristiinstagramcompose.login.data.network.LoginApiClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper   {

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://run.mocky.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //val loginApi: LoginApiClient = getRetrofit().create(LoginApiClient::class.java)
}