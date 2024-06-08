package co.tami.basketball.team.data

import co.tami.basketball.team.domain.entity.PlayerEntity

data class PlayerData(
    val id: String = "",
    val name: String = "",
    val age: Int = -1,
    val jersey: Int = -1,
    val image: String = "",
    val positions: List<PlayerPosition> = listOf(),
    val playerAttributes: PlayerAttributesData = PlayerAttributesData()
)

fun PlayerData.toEntity(): PlayerEntity {

    val attributes = playerAttributes.toAttributesList()
    val overRoll = (attributes.sumOf { it.average }) / attributes.size

    return PlayerEntity(
        id,
        name,
        age,
        jersey,
        image,
        positions.joinToString("/"),
        overRoll,
        attributes
    )
}