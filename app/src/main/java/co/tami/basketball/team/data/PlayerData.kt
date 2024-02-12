package co.tami.basketball.team.data

data class PlayerData(
    val id: Long,
    val name: String,
    val age: String,
    val position: String,
    val attributes: PlayerAttributesData
)