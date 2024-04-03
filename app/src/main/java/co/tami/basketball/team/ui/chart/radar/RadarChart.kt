package co.tami.basketball.team.ui.chart.radar

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import co.tami.basketball.team.ext.toPx
import timber.log.Timber
import kotlin.math.PI


private val DEFAULT_STROKE_CAP: StrokeCap = StrokeCap.Round
private val DEFAULT_STORK_SIZE: Dp = 1.dp

private val labelList = listOf<String>("Party1", "Party2", "Party3", "Party4", "Party5", "Party6")

@Composable
fun DrawPolygonLine(
    lineColor: Color,
    vertexCount: Int,
    statCount: Int,
    modifier: Modifier = Modifier,
    strokeWidth: Float = DEFAULT_STORK_SIZE.toPx(),
    strokeCap: StrokeCap = DEFAULT_STROKE_CAP
) {

    if (vertexCount < 3)
        throw IllegalArgumentException("The minimum number of vertex count is 3.")


    // textMeasurer
    val textMeasurer: TextMeasurer = rememberTextMeasurer()
    val maxLabelWidth =
        measureMaxLabelWidth(labelList, MaterialTheme.typography.labelSmall, textMeasurer)
    val labelHeight = textMeasurer.measure(AnnotatedString("M")).size.height
    Canvas(modifier = modifier) {
        val radius = (size.minDimension / 2f) - (maxLabelWidth + 10.toDp().toPx())  // 반지름
//        val radius = (size.minDimension / 2f) // 반지름
        val labelRadius = (size.minDimension / 2) - (maxLabelWidth / 2)
        val angleBetweenLines = PI * 2 / vertexCount // 정 다각형 꼭짓점을 찍기 위해 각도를 구한다.
        val offsetAngle = -PI / 2

        // StatCount 로 나눈다.
        val startDrawRadius = radius / statCount

        for (statIndex in 1..statCount) {
            val calculatorRadius = startDrawRadius * statIndex
            var startOffset = Calculator.getCircumferencePointOffset(
                center, calculatorRadius, offsetAngle
            )

            for (vertexIndex in 1..vertexCount) {
                val endOffsetAngle = angleBetweenLines * vertexIndex + offsetAngle
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
                if (statIndex == statCount) {

                    // Label
                    val labelTopLeft = Calculator.getCircumferencePointOffset(center, labelRadius, endOffsetAngle)
                    drawText(
                        textMeasurer = textMeasurer,
                        text = "label1",
                        topLeft = Offset(
                            labelTopLeft.x - textMeasurer.measure(
                                AnnotatedString(
                                    text = "label1",
                                ),
                            ).size.width / 2,
                            labelTopLeft.y - labelHeight / 2
                        )
                    )
                    drawLine(
                        color = lineColor,
                        start = center,
                        end = startOffset,
                        strokeWidth = strokeWidth,
                        cap = strokeCap
                    )
                }
            }
        }
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
@Preview
fun DrawPolygonLinePreview() {

    DrawPolygonLine(
        Color.Gray,
        5,
        5,
        modifier = Modifier
            .size(300.dp)
    )
}