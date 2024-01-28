package co.tami.basketball.team.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.tami.basketball.team.data.repo.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
) : ViewModel() {


    init {
        viewModelScope.launch {
            loginRepository.login("test@naver.com", "123456")
        }
    }
}