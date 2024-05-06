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
import androidx.compose.ui.graphics.drawscope.DrawScope
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
    radarValueMap: LinkedHashMap<String, Int>,
    polygonLineColor: Color,
    polygonColor: Color,
    labelTextStyle: TextStyle,
    modifier: Modifier = Modifier,
    scalarSteps: Int = DEFAULT_SCALAR_STEPS,
    polygonLineStrokeWidth: Float = DEFAULT_STORK_SIZE.toPx(),
    polygonLineStrokeCap: StrokeCap = DEFAULT_STROKE_CAP
) {

    val vertexCount = radarValueMap.size

    if (vertexCount < 3)
        throw IllegalArgumentException("The minimum number of vertex count is 3.")


    val textMeasurer = rememberTextMeasurer()
    Canvas(modifier = modifier) {
        val radius = (size.minDimension / 2)
        for (index in 1..scalarSteps) {
            val radiusScalar = radius / scalarSteps * index
            // Polygon Line 그리기
            drawPolygonLine(
                radius = radiusScalar,
                vertexCount = vertexCount,
                color = polygonLineColor,
                strokeWidth = polygonLineStrokeWidth,
                cap = polygonLineStrokeCap
            )
        }

        drawLabels(
            vertexCount = vertexCount,
            textMeasurer = textMeasurer,
            labes = radarValueMap.keys.toList(),
            style = labelTextStyle
        )

        // Polygon 그리기
        drawPolygon(
            radius = radius,
            stats = radarValueMap.values.toList(),
            polygonColor = polygonColor,
            vertexCount = vertexCount
        )
    }
}

fun DrawScope.drawPolygonLine(
    radius: Float,
    vertexCount: Int,
    color: Color,
    strokeWidth: Float,
    cap: StrokeCap
) {
    // 정 다각형 꼭짓점을 찍기 위해 각도를 구한다.
    val angleBetweenLines = PI * 2 / vertexCount

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
    radius: Float,
    stats: List<Int>,
    polygonColor: Color,
    vertexCount: Int
) {

    // 정 다각형 꼭짓점을 찍기 위해 각도를 구한다.
    val angleBetweenLines = PI * 2 / vertexCount

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
        color = polygonColor
    )
}

fun DrawScope.drawLabels(
    vertexCount: Int,
    textMeasurer: TextMeasurer,
    labes: List<String>,
    style: TextStyle
) {
    // RadarChart TextOffset Calculation
    val labelRadius = (size.minDimension / 2)
    val angleBetweenLines = PI * 2 / vertexCount

    labes.forEachIndexed { index, label ->
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
fun DrawPolygonLinePreview2() {
    val map = linkedMapOf(
        "label1" to 80,
        "Party2" to 76,
        "Party3" to 66,
        "Party4" to 43,
        "Party5" to 79,
        "Party6" to 100
    )

    SystemThemeSurface {
        RadarChart(
            map,
            Color.Gray,
            Color(0x6022A3A0),
            MaterialTheme.typography.labelMedium.copy(color = MaterialTheme.colorScheme.secondary),
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        )

    }

}