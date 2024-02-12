package co.tami.basketball.team.data.source

import co.tami.basketball.team.data.PlayerData

interface PlayerDataSource {
    suspend fun getPlayers(): List<PlayerData>
    suspend fun getPlayer(id: Long): PlayerData
}