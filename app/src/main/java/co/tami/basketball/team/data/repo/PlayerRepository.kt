package co.tami.basketball.team.data.repo

import co.tami.basketball.team.domain.entity.PlayerEntity

interface PlayerRepository {
    suspend fun getPlayer(id: Long): PlayerEntity
}