package co.tami.basketball.team.domain.entity

import co.tami.basketball.team.data.PlayerAttributesData
data class PlayerEntity(
    val id: Long,
    val name: String,
    val age: Int,
    val jersey: Int,
    val positions: String,
    val attributes: PlayerAttributesData
)