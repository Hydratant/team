package co.tami.basketball.team.data

import co.tami.basketball.team.domain.entity.PlayerEntity

data class PlayerData(
    val id: Long,
    val name: String,
    val age: Int,
    val jersey: Int,
    val positions: List<PlayerPosition>,
    val attributes: PlayerAttributesData
)

fun PlayerData.toEntity(): PlayerEntity =
    PlayerEntity(id, name, age, jersey, positions.joinToString("/"), attributes.toEntity())