package com.aliceresponde.aristiinstagramcompose.login.domain

import com.aliceresponde.aristiinstagramcompose.login.data.network.repository.LoginRepository

class LoginUseCase {
    private val repository = LoginRepository()
    suspend fun doLogin(user: String, password: String): Boolean = repository.doLogin(user, password)
}