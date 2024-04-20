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

fun PlayerData.toEntity(): PlayerEntity {

    val attributesEntity = attributes.toEntity()

    val outsideScoringAverage = attributesEntity.outsideScoringMap.values.average()
    val insideScoringAverage = attributesEntity.insideScoringMap.values.average()
    val athleticismAverage = attributesEntity.athleticismMap.values.average()
    val playMakingAverage = attributesEntity.playMakingMap.values.average()
    val defendingAverage = attributesEntity.defendingMap.values.average()
    val reboundingAverage = attributesEntity.reboundingMap.values.average()
    val overRoll =
        (outsideScoringAverage + insideScoringAverage + athleticismAverage + playMakingAverage + defendingAverage + reboundingAverage) / 6

    return PlayerEntity(
        id,
        name,
        age,
        jersey,
        positions.joinToString("/"),
        overRoll.toInt(),
        attributes.toEntity()
    )
}