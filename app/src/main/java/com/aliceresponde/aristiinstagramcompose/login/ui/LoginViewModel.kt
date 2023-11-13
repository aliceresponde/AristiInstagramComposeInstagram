package com.aliceresponde.aristiinstagramcompose.login.ui

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    private val _email: MutableLiveData<String> = MutableLiveData()
    val email: LiveData<String> = _email

    private val _password: MutableLiveData<String> = MutableLiveData()
    val password: LiveData<String> = _password

    private val _isEmailAndPasswordValid: MutableLiveData<Boolean> = MutableLiveData()
    val isEmailAndPasswordValid: LiveData<Boolean> = _isEmailAndPasswordValid

    fun onLoginChange(email: String, password: String) {
        _email.value = email
        _password.value = password
        _isEmailAndPasswordValid.value = isEmailAndPasswordValid(email, password)
    }

    private fun isEmailAndPasswordValid(email: String, password: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 5
    }
}
