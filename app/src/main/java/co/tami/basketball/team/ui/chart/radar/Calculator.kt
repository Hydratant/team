package co.tami.basketball.team.ui.chart.radar

import androidx.compose.ui.geometry.Offset
import kotlin.math.cos
import kotlin.math.sin

object Calculator {

    /**
     * 원 좌표 구하기
     *
     * @param center Center
     * @param radius 반지름
     * @param angle 각도
     */
     fun getCircumferencePointOffset(
        center: Offset,
        radius: Float,
        angle: Double
    ) = Offset(
        center.x + radius * cos(angle).toFloat(),
        center.y + radius * sin(angle).toFloat()
    )
}