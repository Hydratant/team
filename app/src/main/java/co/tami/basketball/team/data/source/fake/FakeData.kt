@file:Suppress(
    "NonAsciiCharacters", "PrivatePropertyName", "SpellCheckingInspection",
    "ObjectPropertyName"
)

package co.tami.basketball.team.data.source.fake

import co.tami.basketball.team.data.PlayerAttributesData
import co.tami.basketball.team.data.PlayerData
import co.tami.basketball.team.data.PlayerPosition
import co.tami.basketball.team.data.리바운드데이터
import co.tami.basketball.team.data.수비데이터
import co.tami.basketball.team.data.슛데이터
import co.tami.basketball.team.data.컨트롤데이터
import co.tami.basketball.team.data.패스데이터
import co.tami.basketball.team.data.피지컬데이터

private val 루카피지컬: 피지컬데이터 =
    피지컬데이터(
        속도 = 84,
        가속 = 84,
        힘 = 72,
        지구력 = 98,
        허슬 = 98
    )

private val 루카슛: 슛데이터 =
    슛데이터(
        레이업 = 98,
        미들슛 = 87,
        _3점슛 = 88,
        자유투 = 83,
        포스트훅 = 98,
        센스 = 98,
    )

private val 루카컨트롤: 컨트롤데이터 =
    컨트롤데이터(
        볼컨트롤 = 98,
        볼리시브 = 98,
        드리블속도 = 83
    )

private val 루카패스: 패스데이터 =
    패스데이터(
        정확도 = 98,
        센스 = 98,
        시야 = 96
    )

private val 루카수비: 수비데이터 =
    수비데이터(
        박스아웃 = 66,
        스틸 = 59,
        블락 = 65,
        도움수비 = 74,
        패스차단 = 76,
    )

private val 루카리바운드: 리바운드데이터 =
    리바운드데이터(
        공격 = 68,
        수비 = 81,
        볼키핑 = 73
    )


private val lukaAttributesData: PlayerAttributesData =
    PlayerAttributesData(
        피지컬 = 루카피지컬,
        슛 = 루카슛,
        컨트롤 = 루카컨트롤,
        패스 = 루카패스,
        수비 = 루카수비,
        리바운드 = 루카리바운드
    )


val lukaPlayer: PlayerData = PlayerData(
    id = 0,
    name = "루카 돈치치",
    age = 34,
    jersey = 77,
    image = "test",
    positions = listOf(
        PlayerPosition.PG, PlayerPosition.SG
    ),
    attributes = lukaAttributesData
)


private val 상혁피지컬: 피지컬데이터 =
    피지컬데이터(
        속도 = 84,
        가속 = 84,
        힘 = 72,
        지구력 = 98,
        허슬 = 98
    )

private val 상혁슛: 슛데이터 =
    슛데이터(
        레이업 = 98,
        미들슛 = 87,
        _3점슛 = 88,
        자유투 = 83,
        포스트훅 = 98,
        센스 = 98,
    )

private val 상혁컨트롤: 컨트롤데이터 =
    컨트롤데이터(
        볼컨트롤 = 98,
        볼리시브 = 98,
        드리블속도 = 83
    )

private val 상혁패스: 패스데이터 =
    패스데이터(
        정확도 = 98,
        센스 = 98,
        시야 = 96
    )

private val 상혁수비: 수비데이터 =
    수비데이터(
        박스아웃 = 66,
        스틸 = 59,
        블락 = 65,
        도움수비 = 74,
        패스차단 = 76,
    )

private val 상혁리바운드: 리바운드데이터 =
    리바운드데이터(
        공격 = 68,
        수비 = 81,
        볼키핑 = 73
    )


private val 상혁데이터: PlayerAttributesData =
    PlayerAttributesData(
        피지컬 = 상혁피지컬,
        슛 = 상혁슛,
        컨트롤 = 상혁컨트롤,
        패스 = 상혁패스,
        수비 = 상혁수비,
        리바운드 = 상혁리바운드
    )


val 박상혁: PlayerData = PlayerData(
    id = 1,
    name = "박상혁",
    age = 34,
    jersey = 17,
    image = "test",
    positions = listOf(
        PlayerPosition.PG, PlayerPosition.SF
    ),
    attributes = 상혁데이터
)