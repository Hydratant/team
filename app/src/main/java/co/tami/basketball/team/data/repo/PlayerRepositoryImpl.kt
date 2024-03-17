package co.tami.basketball.team.data.repo

import co.tami.basketball.team.data.PlayerData
import co.tami.basketball.team.data.source.PlayerDataSource

class PlayerRepositoryImpl(
    private val playerDataSource: PlayerDataSource
) : PlayerRepository {
    override suspend fun getPlayer(id: Long): PlayerData =
        playerDataSource.getPlayer(id)
}