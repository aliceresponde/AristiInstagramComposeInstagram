package com.aliceresponde.aristiinstagramcompose.login.ui

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliceresponde.aristiinstagramcompose.login.domain.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {
    private val _email: MutableLiveData<String> = MutableLiveData()
    val email: LiveData<String> = _email

    private val _password: MutableLiveData<String> = MutableLiveData()
    val password: LiveData<String> = _password

    private val _isEmailAndPasswordValid: MutableLiveData<Boolean> = MutableLiveData()
    val isEmailAndPasswordValid: LiveData<Boolean> = _isEmailAndPasswordValid

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    fun onLoginChange(email: String, password: String) {
        _email.value = email
        _password.value = password
        _isEmailAndPasswordValid.value = isEmailAndPasswordValid(email, password)
    }

    fun onDoLogin() {
        viewModelScope.launch(IO) {
            _isLoading.postValue(true)
            val isSuccess = loginUseCase.doLogin(user = email.value!!, password = password.value!!)
            Log.i("LoginViewModel", "onDoLogin: is success ? $isSuccess")
            // if true go to next screen
            _isLoading.postValue(false)
        }
    }

    private fun isEmailAndPasswordValid(email: String, password: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 5
    }
}
