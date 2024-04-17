package co.tami.basketball.team.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.tami.basketball.team.data.repo.PlayerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerDetailViewModel @Inject constructor(
    private val playerRepository: PlayerRepository,
    stateHandle: SavedStateHandle
) : ViewModel() {

    private val id = stateHandle.get<Long>(KEY_PLAYER_ID)

    init {
        getPlayer()
    }

    private fun getPlayer() {
        viewModelScope.launch {
            // TODO : id 가 Null 이 아닐 경우 오류 메세지 후 Finish
            id?.let { id: Long ->
                playerRepository.getPlayer(id)
            }
        }
    }

    companion object {
        const val KEY_PLAYER_ID = "playerId"
    }
}