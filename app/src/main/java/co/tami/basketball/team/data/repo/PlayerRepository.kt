package co.tami.basketball.team.data.repo

import co.tami.basketball.team.data.PlayerData

interface PlayerRepository {
    suspend fun getPlayer(id: Long): PlayerData
}