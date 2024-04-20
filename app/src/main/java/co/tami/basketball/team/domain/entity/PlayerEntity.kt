package co.tami.basketball.team.domain.entity

data class PlayerEntity(
    val id: Long,
    val name: String,
    val age: Int,
    val jersey: Int,
    val positions: String,
    val overRoll: Int,
    val attributes: List<PlayerAttributeEntity>
)