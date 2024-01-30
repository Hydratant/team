package co.tami.basketball.team.ui.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> get() = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> get() = _password

    private val _isVisibilityPassword = MutableStateFlow(false)
    val isVisibilityPassword: StateFlow<Boolean> get() = _isVisibilityPassword

    fun onEmailValueChange(email: String) {
        _email.value = email
    }

    fun onPasswordValueChange(password: String) {
        _password.value = password
    }

    fun isVisibilityPassword(isVisibility: Boolean) {
        _isVisibilityPassword.value = isVisibility
    }

}