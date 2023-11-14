package com.aliceresponde.aristiinstagramcompose.login.data.network.repository

import com.aliceresponde.aristiinstagramcompose.login.data.network.LoginService
import javax.inject.Inject

class LoginRepository @Inject constructor(private val loginService: LoginService) {
    suspend fun doLogin(user: String, password: String): Boolean = loginService.doLogin(user, password)
}