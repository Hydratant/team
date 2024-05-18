package co.tami.basketball.team.data.source.fake

import co.tami.basketball.team.data.PlayerData
import co.tami.basketball.team.data.source.PlayerDataSource

class FakePlayerDataSourceImpl : PlayerDataSource {
    private val fakedPlayers = listOf(
        lukaPlayer, 박상혁
    )

    override suspend fun getPlayers(): List<PlayerData> = fakedPlayers

    override suspend fun getPlayer(id: Long): PlayerData =
        fakedPlayers.find { player: PlayerData -> player.id == id }
            ?: throw IllegalArgumentException("Player not found")
}