package co.tami.basketball.team.data

import co.tami.basketball.team.domain.entity.PlayerAttributeEntity
import co.tami.basketball.team.ext.toMap
import kotlinx.serialization.Serializable

data class PlayerAttributesData(
    val outsideScoringData: OutsideScoringData,
    val insideScoringData: InsideScoringData,
    val athleticismData: AthleticismData,
    val playMakingData: PlayMakingData,
    val defendingData: DefendingData,
    val reboundingData: ReboundingData
)

fun Map<String, Int>.toPlayerAttributeEntity(title: String): PlayerAttributeEntity {
    return PlayerAttributeEntity(
        title,
        values.average().toInt(),
        this,
    )
}

fun PlayerAttributesData.toAttributesList(): List<PlayerAttributeEntity> {

    val outSideScoringEntity = outsideScoringData
        .toMap()
        .toPlayerAttributeEntity("outsideScoring")

    val insideScoringEntity = insideScoringData
        .toMap()
        .toPlayerAttributeEntity("insideScoring")

    val athleticismEntity = athleticismData
        .toMap()
        .toPlayerAttributeEntity("athleticism")

    val playMakingEntity = playMakingData
        .toMap()
        .toPlayerAttributeEntity("playMaking")

    val defendingEntity = defendingData
        .toMap()
        .toPlayerAttributeEntity("defending")

    val reboundingEntity = reboundingData
        .toMap()
        .toPlayerAttributeEntity("rebounding")

    return listOf(
        outSideScoringEntity,
        insideScoringEntity,
        athleticismEntity,
        playMakingEntity,
        defendingEntity,
        reboundingEntity
    )
}

@Serializable
data class OutsideScoringData(
    val closeShot: Int,
    val midRangeShot: Int,
    val threePointShot: Int,
    val freeThrow: Int,
    val shotIQ: Int,
    val offensiveConsistency: Int
)

@Serializable
data class InsideScoringData(
    val layup: Int,
    val postHook: Int,
    val postFad: Int,
    val postControl: Int,
    val drawFoul: Int,
    val hands: Int
)

@Serializable
data class AthleticismData(
    val speed: Int,
    val acceleration: Int,
    val strength: Int,
    val stamina: Int,
    val hustle: Int,
    val overallDurability: Int
)

@Serializable
data class PlayMakingData(
    val passAccuracy: Int,
    val ballHandle: Int,
    val speedWithBall: Int,
    val passIQ: Int,
    val passVision: Int
)

@Serializable
data class DefendingData(
    val interiorDefense: Int,
    val perimeterDefense: Int,
    val steal: Int,
    val block: Int,
    val lateralQuickness: Int,
    val helpDefenseIQ: Int,
    val passPerception: Int,
    val defensiveConsistency: Int
)

@Serializable
data class ReboundingData(
    val offensiveRebound: Int,
    val defensiveRebound: Int
)