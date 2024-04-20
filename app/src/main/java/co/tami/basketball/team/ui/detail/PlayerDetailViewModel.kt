package co.tami.basketball.team.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.tami.basketball.team.data.repo.PlayerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerDetailViewModel @Inject constructor(
    private val playerRepository: PlayerRepository,
    stateHandle: SavedStateHandle
) : ViewModel() {

    private val id = stateHandle.get<Long>(KEY_PLAYER_ID) ?: 1234L

    // 이름
    private val _name: MutableStateFlow<String> = MutableStateFlow("")
    val name: StateFlow<String> get() = _name.asStateFlow()

    // 포지션
    private val _position: MutableStateFlow<String> = MutableStateFlow("")
    val position: StateFlow<String> get() = _position.asStateFlow()

    // 나이
    private val _age: MutableStateFlow<String> = MutableStateFlow("")
    val age: StateFlow<String> get() = _age.asStateFlow()

    // 등번호
    private val _jersey: MutableStateFlow<String> = MutableStateFlow("")
    val jersey: StateFlow<String> get() = _jersey.asStateFlow()

    init {
        getPlayer()
    }

    private fun getPlayer() {
        viewModelScope.launch {
            // TODO : id 가 Null 이 아닐 경우 오류 메세지 후 Finish
            id?.let { id: Long ->
                val player = playerRepository.getPlayer(id)
                _name.value = player.name
                _age.value = player.age.toString()
                _position.value = player.positions.joinToString("/")
                _jersey.value = player.jersey.toString()

            }
        }
    }

    companion object {
        const val KEY_PLAYER_ID = "playerId"
    }
}