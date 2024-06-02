package co.tami.basketball.team.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.tami.basketball.team.data.repo.PlayerRepository
import co.tami.basketball.team.domain.entity.PlayerAttributeEntity
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

    private val id = stateHandle.get<String>(KEY_PLAYER_ID) ?: ""

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

    // OverRoll
    private val _overRoll: MutableStateFlow<String> = MutableStateFlow("")
    val overRoll: StateFlow<String> get() = _overRoll.asStateFlow()

    // OverRoll
    private val _attributes: MutableStateFlow<List<PlayerAttributeEntity>> =
        MutableStateFlow(listOf())
    val attributes: StateFlow<List<PlayerAttributeEntity>> get() = _attributes.asStateFlow()

    private val _bottomSheetEvent = MutableStateFlow<BottomSheetEvent>(BottomSheetEvent.Hide)
    val bottomSheetEvent: StateFlow<BottomSheetEvent> get() = _bottomSheetEvent.asStateFlow()

    init {
        getPlayer()
    }

    private fun getPlayer() {
        viewModelScope.launch {
            // TODO : id 가 Null 이 아닐 경우 오류 메세지 후 Finish
            id.let { id: String ->
                val player = playerRepository.getPlayer(id)
                _name.value = player.name
                _age.value = player.age.toString()
                _position.value = player.positions
                _jersey.value = player.jersey.toString()
                _overRoll.value = player.overRoll.toString()
                _attributes.value = player.attributes
            }
        }
    }

    fun showBottomSheet(item: PlayerAttributeEntity) {
        _bottomSheetEvent.value = BottomSheetEvent.Show(item)
    }

    fun hideBottomSheet() {
        _bottomSheetEvent.value = BottomSheetEvent.Hide
    }

    companion object {
        const val KEY_PLAYER_ID = "playerId"
    }

    sealed class BottomSheetEvent {
        data class Show(val item: PlayerAttributeEntity) : BottomSheetEvent()
        data object Hide : BottomSheetEvent()
    }
}