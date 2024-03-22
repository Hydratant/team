package co.tami.basketball.team.ui.chart.radar

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.PI


@Composable
fun DrawPolygonLine(
    vertexCount: Int,
    statCount:Int,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier) {
        val radius = size.minDimension / 2f // 반지름

        val drawRadius = radius / statCount
        // 꼭지점을 구하기 위한 offset -PI / 2 는 -180도기 때문에 무조건 12시 방향부터 시작.
        val offsetAngle = -PI / 2
        val angleBetweenLines = PI * 2 / vertexCount // 호도법에 의해서 PI * 2 는 360

        for (i in 1..statCount) {
            val calculatorRadius = drawRadius * i

            var startOffset = Calculator.getCircumferencePointOffset(
                center, calculatorRadius, offsetAngle
            )
            for (i in 0 until vertexCount) {
                val endOffset = Calculator.getCircumferencePointOffset(
                    center,
                    calculatorRadius,
                    (i + 1) * angleBetweenLines + offsetAngle
                )
                drawLine(
                    color = Color.Blue,
                    start = startOffset,
                    end = endOffset,
                    strokeWidth = 2f,
                    cap = StrokeCap.Round
                )
                startOffset = endOffset
                drawLine(
                    color = Color.Gray,
                    start = center,
                    end = startOffset,
                    strokeWidth = 2f,
                    cap = StrokeCap.Round
                )
            }
        }
    }
}

@Composable
@Preview
fun DrawPolygonLinePreview() {

    DrawPolygonLine(
        8,
        5,
        modifier = Modifier
            .size(300.dp)
    )
}