package co.tami.basketball.team.ui.chart.radar

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import co.tami.basketball.team.ext.toPx
import kotlin.math.PI


private val DEFAULT_STROKE_CAP: StrokeCap = StrokeCap.Round
private val DEFAULT_STORK_SIZE: Dp = 1.dp

@Composable
fun DrawPolygonLine(
    lineColor: Color,
    vertexCount: Int,
    statCount: Int,
    modifier: Modifier = Modifier,
    strokeWidth: Float = DEFAULT_STORK_SIZE.toPx(),
    strokeCap: StrokeCap = DEFAULT_STROKE_CAP
) {
    Canvas(modifier = modifier) {
        val radius = size.minDimension / 2f // 반지름

        val angleBetweenLines = PI * 2 / vertexCount // 정 다각형 꼭짓점을 찍기 위해 각도를 구한다.
        val offsetAngle = -PI / 2

        // StatCount 로 나눈다.
        val startDrawRadius = radius / statCount

        for (i in 1..statCount) {
            val calculatorRadius = startDrawRadius * i
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
                if (i == statCount) {
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

@Composable
@Preview
fun DrawPolygonLinePreview() {

    DrawPolygonLine(
        Color.Gray,
        8,
        5,
        modifier = Modifier
            .size(300.dp)
    )
}