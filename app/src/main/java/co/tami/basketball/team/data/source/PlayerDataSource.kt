package co.tami.basketball.team.data.source

import co.tami.basketball.team.data.PlayerData

interface PlayerDataSource {
    suspend fun getPlayer(id: Long): PlayerData
}