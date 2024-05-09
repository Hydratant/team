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
            클로즈슛 = 98,
            미들슛 = 87,
            삼점슛 = 88,
            자유투 = 83,
            슛센스 = 98,
            공격일관성 = 98,
        )

    private val lukaInsideScoringData: InsideScoringData =
        InsideScoringData(
            레이업 = 98,
            근접샷 = 85,
            포스트페이드 = 85,
            포스트동작 = 87,
            파울유도 = 98,
            볼소유 = 98
        )

    private val lukaAthleticismData: AthleticismData =
        AthleticismData(
            속도 = 84,
            가속 = 84,
            힘 = 72,
            내구성 = 98,
            허슬 = 98
        )

    private val lukaPlayMakingData: PlayMakingData =
        PlayMakingData(
            패스정확도 = 98,
            볼핸들 = 98,
            공이있을때속도 = 83,
            패스센스 = 98,
            패스시야 = 96
        )

    private val lukaDefendingData: DefendingData =
        DefendingData(
            내부방어 = 66,
            외곽방어 = 74,
            스틸 = 59,
            블락 = 65,
            측면신속성 = 75,
            도움수비 = 74,
            패스인식 = 76,
            방어일관성 = 75
        )

    private val lukaReboundingData: ReboundingData =
        ReboundingData(
            공격리바운드 = 68,
            수비리바운드 = 81
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