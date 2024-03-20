package co.tami.basketball.team.ui.chart.radar

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.PI


@Composable
fun DrawLines(
    modifier: Modifier = Modifier,
    num: Int
) {
    Canvas(modifier = modifier) {
        // Canvas 중앙 Offset
        val center = Offset(size.width / 2, size.height / 2)
        val radius = size.minDimension / 2f // 반지름
        val angleBetweenLines = PI * 2 / num // 호도법에 의해서 PI * 2 는 360

        // 꼭지점을 구하기 위한 offset -PI / 2 는 -180도기 때문에 무조건 12시 방향부터 시작.
        val offsetAngle = -PI / 2

        var startOffset = Calculator.getCircumferencePointOffset(
            center, radius, offsetAngle
        )
        for (i in 0 until num) {
            val endOffset = Calculator.getCircumferencePointOffset(
                center,
                radius,
                (i + 1) * angleBetweenLines  + offsetAngle
            )
            drawLine(
                color = Color.Blue,
                start = startOffset,
                end = endOffset,
                strokeWidth = 2f,
                cap = StrokeCap.Round
            )
            startOffset = endOffset
        }
    }
}


@Composable
@Preview
fun DrawLinesPreview() {

    DrawLines(
        modifier = Modifier
            .size(300.dp),
        6
    )
}