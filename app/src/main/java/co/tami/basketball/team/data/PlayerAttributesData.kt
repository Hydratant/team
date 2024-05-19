@file:Suppress("NonAsciiCharacters", "PropertyName", "SpellCheckingInspection", "ClassName")

package co.tami.basketball.team.data

import co.tami.basketball.team.domain.entity.PlayerAttributeEntity
import co.tami.basketball.team.ext.toMap
import kotlinx.serialization.Serializable

data class PlayerAttributesData(
    val 피지컬: 피지컬데이터,
    val 슛: 슛데이터,
    val 컨트롤: 컨트롤데이터,
    val 패스: 패스데이터,
    val 수비: 수비데이터,
    val 리바운드: 리바운드데이터
)

@Serializable
data class 피지컬데이터(
    val 힘: Int,
    val 지구력: Int,
    val 허슬: Int,
    val 가속: Int,
    val 속도: Int
)

@Serializable
data class 슛데이터(
    val _3점슛: Int,
    val 센스: Int,
    val 레이업: Int,
    val 미들슛: Int,
    val 자유투: Int,
    val 포스트훅: Int
)

@Serializable
data class 컨트롤데이터(
    val 볼컨트롤: Int,
    val 볼리시브: Int,
    val 드리블속도: Int
)

@Serializable
data class 패스데이터(
    val 센스: Int,
    val 정확도: Int,
    val 시야: Int
)

@Serializable
data class 수비데이터(
    val 스틸: Int,
    val 블락: Int,
    val 박스아웃: Int,
    val 도움수비: Int,
    val 패스차단: Int
)

@Serializable
data class 리바운드데이터(
    val 수비: Int,
    val 공격: Int,
    val 볼키핑: Int
)

fun Map<String, Int>.toPlayerAttributeEntity(title: String): PlayerAttributeEntity {
    return PlayerAttributeEntity(
        title,
        values.average().toInt(),
        this,
    )
}

@Suppress("LocalVariableName")
fun PlayerAttributesData.toAttributesList(): List<PlayerAttributeEntity> {

    val 피지컬 = 피지컬
        .toMap()
        .toPlayerAttributeEntity("피지컬")

    val 슛 = 슛
        .toMap()
        .toPlayerAttributeEntity("슛")

    val 컨트롤 = 컨트롤
        .toMap()
        .toPlayerAttributeEntity("컨트롤")

    val 패스 = 패스
        .toMap()
        .toPlayerAttributeEntity("패스")

    val 수비 = 수비
        .toMap()
        .toPlayerAttributeEntity("수비")

    val 리바운드 = 리바운드
        .toMap()
        .toPlayerAttributeEntity("리바운드")

    return listOf(
        피지컬,
        슛,
        컨트롤,
        패스,
        수비,
        리바운드
    )
}
