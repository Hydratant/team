package co.tami.basketball.team.ui.chart.radar

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextMeasurer
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

    fun calculatorLabelOffset(
        labelTopOffset: Offset,
        text: String,
        textMeasurer: TextMeasurer
    ): Offset {
        return Offset(
            labelTopOffset.x - textMeasurer.measure(
                AnnotatedString(text = text),
            ).size.width / 2,
            labelTopOffset.y - textMeasurer.measure(
                AnnotatedString(text = text),
            ).size.height / 2
        )

    }
}