package co.tami.basketball.team.data.source.fake

import co.tami.basketball.team.data.AthleticismData
import co.tami.basketball.team.data.DefendingData
import co.tami.basketball.team.data.InsideScoringData
import co.tami.basketball.team.data.OutsideScoringData
import co.tami.basketball.team.data.PlayMakingData
import co.tami.basketball.team.data.PlayerAttributesData
import co.tami.basketball.team.data.PlayerData
import co.tami.basketball.team.data.PlayerPosition
import co.tami.basketball.team.data.ReboundingData
import co.tami.basketball.team.data.source.PlayerDataSource

class FakePlayerDataSourceImpl : PlayerDataSource {

    private val lukaOutsideScoringData: OutsideScoringData =
        OutsideScoringData(
            closeShot = 98,
            midRangeShot = 87,
            threePointShot = 88,
            freeThrow = 83,
            shotIQ = 98,
            offensiveConsistency = 98,
        )

    private val lukaInsideScoringData: InsideScoringData =
        InsideScoringData(
            layup = 98,
            postHook = 85,
            postFad = 85,
            postControl = 87,
            drawFoul = 98,
            hands = 98
        )

    private val lukaAthleticismData: AthleticismData =
        AthleticismData(
            speed = 84,
            acceleration = 84,
            strength = 72,
            stamina = 98,
            hustle = 98,
            overallDurability = 85
        )

    private val lukaPlayMakingData: PlayMakingData =
        PlayMakingData(
            passAccuracy = 98,
            ballHandle = 98,
            speedWithBall = 83,
            passIQ = 98,
            passVision = 96
        )

    private val lukaDefendingData: DefendingData =
        DefendingData(
            interiorDefense = 66,
            perimeterDefense = 74,
            steal = 59,
            block = 65,
            lateralQuickness = 75,
            helpDefenseIQ = 74,
            passPerception = 76,
            defensiveConsistency = 75
        )

    private val lukaReboundingData: ReboundingData =
        ReboundingData(
            offensiveRebound = 68,
            defensiveRebound = 81
        )


    private val lukaAttributesData: PlayerAttributesData =
        PlayerAttributesData(
            outsideScoringData = lukaOutsideScoringData,
            insideScoringData = lukaInsideScoringData,
            athleticismData = lukaAthleticismData,
            playMakingData = lukaPlayMakingData,
            defendingData = lukaDefendingData,
            reboundingData = lukaReboundingData
        )


    private val lukaPlayer: PlayerData = PlayerData(
        id = 0,
        name = "루카 돈치치",
        age = 34,
        jersey = 77,
        positions = listOf(PlayerPosition.PG, PlayerPosition.SG),
        attributes = lukaAttributesData
    )

    override suspend fun getPlayer(id: Long): PlayerData = lukaPlayer
}