package co.tami.basketball.team.domain.entity

data class PlayerAttributeEntity(
    val title: String,
    val average: Int,
    val value: Map<String, Int>
)