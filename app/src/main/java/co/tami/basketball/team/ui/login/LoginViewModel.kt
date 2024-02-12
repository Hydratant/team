package co.tami.basketball.team.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.tami.basketball.team.data.repo.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {

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

    fun login(
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            loginRepository.login(email, password)
        }
    }

}