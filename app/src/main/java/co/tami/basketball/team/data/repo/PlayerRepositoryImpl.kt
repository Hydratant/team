package co.tami.basketball.team.data.repo

import co.tami.basketball.team.data.PlayerData
import co.tami.basketball.team.data.source.PlayerDataSource
import co.tami.basketball.team.data.toEntity
import co.tami.basketball.team.domain.entity.PlayerEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PlayerRepositoryImpl(
    private val playerDataSource: PlayerDataSource
) : PlayerRepository {
    override fun getPlayers(): Flow<List<PlayerEntity>> = flow {
        emit(playerDataSource.getPlayers().map { data: PlayerData -> data.toEntity() })
    }

    override suspend fun getPlayer(id: String): PlayerEntity =
        playerDataSource.getPlayer(id).toEntity()
}