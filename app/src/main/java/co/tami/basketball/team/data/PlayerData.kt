package co.tami.basketball.team.data

data class PlayerData(
    val id: Long,
    val name: String,
    val age: Int,
    val height: Int,
    val jersey: Int,
    val position: List<PlayerPosition>,
    val attributes: PlayerAttributesData
)