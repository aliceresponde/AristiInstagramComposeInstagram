package com.aliceresponde.aristiinstagramcompose.login.data.network

import com.aliceresponde.aristiinstagramcompose.core.network.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginService {
    // it should receive RetrofitInstance  as a parameter
    private val loginApiClient = RetrofitHelper.getRetrofit().create(LoginApiClient::class.java)

    suspend fun doLogin(user: String, password: String): Boolean {
        return withContext(Dispatchers.IO) {
            val response = loginApiClient.doLogin()
            response.body()?.success ?: false
        }
    }
}