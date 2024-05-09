@file:Suppress("NonAsciiCharacters", "PropertyName", "SpellCheckingInspection")

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
        .toPlayerAttributeEntity("외부슛능력")

    val insideScoringEntity = insideScoringData
        .toMap()
        .toPlayerAttributeEntity("내부슛능력")

    val athleticismEntity = athleticismData
        .toMap()
        .toPlayerAttributeEntity("운동능력")

    val playMakingEntity = playMakingData
        .toMap()
        .toPlayerAttributeEntity("플레이메이킹")

    val defendingEntity = defendingData
        .toMap()
        .toPlayerAttributeEntity("수비능력")

    val reboundingEntity = reboundingData
        .toMap()
        .toPlayerAttributeEntity("리바운드")

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
    val 클로즈슛: Int,
    val 미들슛: Int,
    val 삼점슛: Int,
    val 자유투: Int,
    val 슛센스: Int,
    val 공격일관성: Int
)

/**
 *
 *
 * @property 레이업
 * @property 근접샷
 * @property 포스트페이드 포스트 업 자세에서 점프슛을 할 때 쓰는 것
 * @property 포스트동작 포스트 컨트롤은 드롭 스텝, 스핀 동작, 업 앤 언더, 피벗 및 업 페이크와 같은 포스트 동작을 위한 것입니다.
 * @property 파울유도
 * @property 볼소유
 */
@Serializable
data class InsideScoringData(
    val 레이업: Int,
    val 근접샷: Int,
    val 포스트페이드: Int,
    val 포스트동작: Int,
    val 파울유도: Int,
    val 볼소유: Int
)

@Serializable
data class AthleticismData(
    val 속도: Int,
    val 가속: Int,
    val 힘: Int,
    val 내구성: Int,
    val 허슬: Int
)

@Serializable
data class PlayMakingData(
    val 패스정확도: Int,
    val 볼핸들: Int,
    val 공이있을때속도: Int,
    val 패스센스: Int,
    val 패스시야: Int
)

@Serializable
data class DefendingData(
    val 내부방어: Int,
    val 외곽방어: Int,
    val 스틸: Int,
    val 블락: Int,
    val 측면신속성: Int,
    val 도움수비: Int,
    val 패스인식: Int,
    val 방어일관성: Int
)

@Serializable
data class ReboundingData(
    val 공격리바운드: Int,
    val 수비리바운드: Int
)