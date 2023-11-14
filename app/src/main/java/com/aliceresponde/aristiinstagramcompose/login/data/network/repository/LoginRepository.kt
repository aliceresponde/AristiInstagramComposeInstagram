package com.aliceresponde.aristiinstagramcompose.login.data.network.repository

import com.aliceresponde.aristiinstagramcompose.login.data.network.LoginService

class LoginRepository {
    private val loginService = LoginService()
    suspend fun doLogin(user: String, password: String) :Boolean = loginService.doLogin(user, password)
}