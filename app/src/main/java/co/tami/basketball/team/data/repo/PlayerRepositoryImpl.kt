package co.tami.basketball.team.data.repo

import co.tami.basketball.team.data.source.PlayerDataSource
import co.tami.basketball.team.data.toEntity
import co.tami.basketball.team.domain.entity.PlayerEntity

class PlayerRepositoryImpl(
    private val playerDataSource: PlayerDataSource
) : PlayerRepository {
    override suspend fun getPlayer(id: Long): PlayerEntity =
        playerDataSource.getPlayer(id).toEntity()
}