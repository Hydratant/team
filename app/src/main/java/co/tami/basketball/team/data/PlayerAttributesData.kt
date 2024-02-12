package co.tami.basketball.team.data

data class PlayerAttributesData(
    val outsideScoringData: OutsideScoringData,
    val insideScoringData: InsideScoringData,
    val athleticismData: AthleticismData,
    val playMakingData: PlayMakingData,
    val defendingData: DefendingData,
    val reboundingData: ReboundingData
)

data class OutsideScoringData(
    val closeShot: Int,
    val midRangeShot: Int,
    val threePointShot: Int,
    val freeThrow: Int,
    val shotIQ: Int,
    val offensiveConsistency: Int
)

data class InsideScoringData(
    val layup: Int,
    val postHook: Int,
    val postFad: Int,
    val postControl: Int,
    val drawFoul: Int,
    val hands: Int
)

data class AthleticismData(
    val speed: Int,
    val acceleration: Int,
    val strength: Int,
    val stamina: Int,
    val hustle: Int,
    val overallDurability: Int
)

data class PlayMakingData(
    val passAccuracy: Int,
    val ballHandle: Int,
    val speedWithBall: Int,
    val passIQ: Int,
    val passVision: Int
)

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

data class ReboundingData(
    val offensiveRebound: Int,
    val defensiveRebound: Int
)