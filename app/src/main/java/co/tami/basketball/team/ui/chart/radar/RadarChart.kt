@file:Suppress("SpellCheckingInspection")

package co.tami.basketball.team.ui.chart.radar

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import co.tami.basketball.team.ext.toPx
import co.tami.basketball.team.ui.common.DarkLightModePreview
import co.tami.basketball.team.ui.common.SystemThemeSurface
import kotlin.math.PI


private val DEFAULT_STROKE_CAP: StrokeCap = StrokeCap.Round
private val DEFAULT_STORK_SIZE: Dp = 1.dp
private const val DEFAULT_SCALAR_STEPS = 5


@Composable
fun RadarChart(
    lineColor: Color,
    stats: List<Int>,
    statColor: Color,
    labels: List<String>,
    textStyle: TextStyle,
    modifier: Modifier = Modifier,
    scalarSteps: Int = DEFAULT_SCALAR_STEPS,
    strokeWidth: Float = DEFAULT_STORK_SIZE.toPx(),
    strokeCap: StrokeCap = DEFAULT_STROKE_CAP
) {

    if (stats.size < 3)
        throw IllegalArgumentException("The minimum number of vertex count is 3.")


    // textMeasurer
    val textMeasurer: TextMeasurer = rememberTextMeasurer()
    val maxLabelWidth =
        measureMaxLabelWidth(labels, textStyle, textMeasurer)

    Canvas(modifier = modifier) {
        // 반지름 에서 Label 영역을 위해 maxLabelWidth, 10dp 패딩을 빼준다.
        val radius = (size.minDimension / 2) - (maxLabelWidth + 10.dp.toPx())
        val labelRadius = (size.minDimension / 2) - (maxLabelWidth / 2)
        val angleBetweenLines = PI * 2 / stats.size // 정 다각형 꼭짓점을 찍기 위해 각도를 구한다.
        val offsetAngle = -PI / 2

        // scalarSteps으로 Stat 구분을 나눈다.
        val startDrawRadius = radius / scalarSteps

        for (scalarStep in 1..scalarSteps) {
            val calculatorRadius = startDrawRadius * scalarStep
            // 시작 Offset
            var startOffset = Calculator.getCircumferencePointOffset(
                center, calculatorRadius, offsetAngle
            )

            for (stat in 1..stats.size) {
                val endOffsetAngle = angleBetweenLines * stat + offsetAngle
                val endOffset = Calculator.getCircumferencePointOffset(
                    center,
                    calculatorRadius,
                    endOffsetAngle
                )
                // 정 다각형 그리기
                drawLine(
                    color = lineColor,
                    start = startOffset,
                    end = endOffset,
                    strokeWidth = strokeWidth,
                    cap = strokeCap
                )

                startOffset = endOffset

                // 원의 중심에서 꼭짓점까지 선을 그리기
                // 한번만 그리기 위해 마지막 Stat 다각형을 그릴때 그린다.
                if (scalarStep == scalarSteps) {

                    // StartOffset이 꼭짓점.
                    drawLine(
                        color = lineColor,
                        start = center,
                        end = startOffset,
                        strokeWidth = strokeWidth,
                        cap = strokeCap
                    )

                    // Label 적용
                    val labelTopOffset =
                        Calculator.getCircumferencePointOffset(center, labelRadius, endOffsetAngle)
                    val labelIndex = stat - 1
                    val labelText = labels[labelIndex]
                    val labelTopLeft = Calculator.calculatorLabelOffset(
                        labelTopOffset,
                        labelText,
                        textMeasurer
                    )
                    // Label 그리기
                    drawText(
                        textMeasurer = textMeasurer,
                        text = labelText,
                        style = textStyle,
                        topLeft = labelTopLeft
                    )
                }
            }
        }

        // Polygon 그리기
        val statsOffsets = mutableListOf<Offset>()
        for (statIndex in 1..stats.size) {
            val realIndex = statIndex - 1
            val statRadius = (stats[realIndex] / 100.0) * radius
            val endOffsetAngle = angleBetweenLines * statIndex + offsetAngle
            val statOffset =
                Calculator.getCircumferencePointOffset(center, statRadius.toFloat(), endOffsetAngle)

            statsOffsets.add(statOffset)
        }
        val path = Path().apply {
            moveTo(statsOffsets[0].x, statsOffsets[0].y)
            statsOffsets.forEach { offset: Offset ->
                lineTo(offset.x, offset.y)
            }
            close()
        }
        drawPath(
            path = path,
            color = statColor
        )

    }
}


/**
 * 라벨들의 최대 width를 계산한다.
 *
 * @param radarLabels label 목록
 * @param labelsStyle label TextStyle
 * @param textMeasurer textMeasurer
 * @return max Label width
 */
private fun measureMaxLabelWidth(
    radarLabels: List<String>,
    labelsStyle: TextStyle,
    textMeasurer: TextMeasurer
): Float {
    return textMeasurer.measure(
        AnnotatedString(
            text = radarLabels.maxByOrNull { it.length } ?: "",
        ), style = labelsStyle
    ).size.width.toFloat()
}


@Composable
@DarkLightModePreview
fun DrawPolygonLinePreview() {
    val labels = listOf("label1", "Party2", "Party3", "Party4", "Party5", "Party6")
    val stats = listOf(15, 60, 30, 40, 55, 100)

    SystemThemeSurface {
        RadarChart(
            Color.Gray,
            stats,
            Color(0x6022A3A0),
            labels,
            MaterialTheme.typography.labelMedium.copy(color = MaterialTheme.colorScheme.secondary),
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        )

    }

}