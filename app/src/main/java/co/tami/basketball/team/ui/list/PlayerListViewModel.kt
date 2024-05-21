package co.tami.basketball.team.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.tami.basketball.team.data.repo.PlayerRepository
import co.tami.basketball.team.domain.entity.PlayerEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class PlayerListViewModel @Inject constructor(
    private val playerRepository: PlayerRepository
) : ViewModel() {
    val players: StateFlow<List<PlayerEntity>>
        get() = playerRepository.getPlayers().stateIn(
            initialValue = emptyList(),
            started = SharingStarted.WhileSubscribed(5000),
            scope = viewModelScope
        )
}