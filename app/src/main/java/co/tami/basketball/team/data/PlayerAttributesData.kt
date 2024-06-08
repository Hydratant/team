@file:Suppress("NonAsciiCharacters", "PropertyName", "SpellCheckingInspection", "ClassName")

package co.tami.basketball.team.data

import co.tami.basketball.team.domain.entity.PlayerAttributeEntity
import co.tami.basketball.team.ext.toMap
import kotlinx.serialization.Serializable

data class PlayerAttributesData(
    val 피지컬: 피지컬데이터 = 피지컬데이터(),
    val 슛: 슛데이터 = 슛데이터(),
    val 컨트롤: 컨트롤데이터 = 컨트롤데이터(),
    val 패스: 패스데이터 = 패스데이터(),
    val 수비: 수비데이터 = 수비데이터(),
    val 리바운드: 리바운드데이터 = 리바운드데이터()
)

@Serializable
data class 피지컬데이터(
    val 힘: Int = 0,
    val 지구력: Int = 0,
    val 허슬: Int = 0,
    val 가속: Int = 0,
    val 속도: Int = 0
)

@Serializable
data class 슛데이터(
    val _3점슛: Int = 0,
    val 센스: Int = 0,
    val 레이업: Int = 0,
    val 미들슛: Int = 0,
    val 자유투: Int = 0,
    val 포스트훅: Int = 0
)

@Serializable
data class 컨트롤데이터(
    val 볼컨트롤: Int = 0,
    val 볼리시브: Int = 0,
    val 드리블속도: Int = 0
)

@Serializable
data class 패스데이터(
    val 센스: Int = 0,
    val 정확도: Int = 0,
    val 시야: Int = 0
)

@Serializable
data class 수비데이터(
    val 스틸: Int = 0,
    val 블락: Int = 0,
    val 박스아웃: Int = 0,
    val 도움수비: Int = 0,
    val 패스차단: Int = 0
)

@Serializable
data class 리바운드데이터(
    val 수비: Int = 0,
    val 공격: Int = 0,
    val 볼키핑: Int = 0
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
