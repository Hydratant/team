package co.tami.basketball.team.domain.entity

data class PlayerAttributesEntity(
    val outsideScoringMap: Map<String, Int>,
    val insideScoringMap: Map<String, Int>,
    val athleticismMap: Map<String, Int>,
    val playMakingMap: Map<String, Int>,
    val defendingMap: Map<String, Int>,
    val reboundingMap: Map<String, Int>
)