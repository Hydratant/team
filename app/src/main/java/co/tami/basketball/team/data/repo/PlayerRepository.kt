package co.tami.basketball.team.data.repo

import co.tami.basketball.team.domain.entity.PlayerEntity
import kotlinx.coroutines.flow.Flow

interface PlayerRepository {

    fun getPlayers(): Flow<List<PlayerEntity>>
    suspend fun getPlayer(id: String): PlayerEntity
}