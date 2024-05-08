package co.tami.basketball.team.ui.chart.radar

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText

fun DrawScope.drawPolygonLine(
    radius: Float,
    vertexCount: Int,
    angleBetweenLines: Double,
    color: Color,
    strokeWidth: Float,
    cap: StrokeCap
) {

    // 시작 Offset
    var startOffset = Calculator.getCircumferencePointOffset(center, radius, 0.0)

    for (index in 1..vertexCount) {
        val endOffsetAngle = angleBetweenLines * index
        val endOffset = Calculator.getCircumferencePointOffset(
            center,
            radius,
            endOffsetAngle
        )
        // 정 다각형 그리기
        drawLine(
            color = color,
            start = startOffset,
            end = endOffset,
            strokeWidth = strokeWidth,
            cap = cap
        )

        startOffset = endOffset

        // 원의 중심에서 꼭짓점까지 선을 그리기
        drawLine(
            color = color,
            start = center,
            end = startOffset,
            strokeWidth = strokeWidth,
            cap = cap
        )
    }

}

fun DrawScope.drawPolygon(
    angleBetweenLines: Double,
    radius: Float,
    stats: List<Int>,
    polygonColor: Color,
    style: DrawStyle = Fill
) {

    // Offset 저장
    val statsOffsets = mutableListOf<Offset>()
    for (statIndex in 1..stats.size) {
        val realIndex = statIndex - 1
        val statRadius = (stats[realIndex] / 100.0) * radius
        val endOffsetAngle = angleBetweenLines * statIndex
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
        color = polygonColor,
        style = style
    )
}

fun DrawScope.drawLabels(
    angleBetweenLines: Double,
    maxLabelWidth: Float,
    textMeasurer: TextMeasurer,
    labels: List<String>,
    style: TextStyle
) {
    // RadarChart TextOffset Calculation
    val labelRadius = (size.minDimension / 2) - (maxLabelWidth / 2)

    labels.forEachIndexed { index, label ->
        val endOffsetAngle = angleBetweenLines * (index + 1)
        val labelTopOffset =
            Calculator.getCircumferencePointOffset(center, labelRadius, endOffsetAngle)

        val labelTopLeft = Calculator.calculatorLabelOffset(
            labelTopOffset,
            label,
            textMeasurer
        )

        drawText(
            textMeasurer = textMeasurer,
            text = label,
            style = style,
            topLeft = labelTopLeft
        )

    }
}