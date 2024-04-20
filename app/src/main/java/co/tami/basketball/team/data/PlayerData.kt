package co.tami.basketball.team.data

data class PlayerData(
    val id: Long,
    val name: String,
    val age: Int,
    val jersey: Int,
    val positions: List<PlayerPosition>,
    val attributes: PlayerAttributesData
)