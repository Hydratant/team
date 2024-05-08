@file:Suppress("SpellCheckingInspection")

package co.tami.basketball.team.ui.chart.radar

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import co.tami.basketball.team.ext.toPx
import co.tami.basketball.team.ui.common.DarkLightModePreview
import co.tami.basketball.team.ui.common.SystemThemeSurface
import kotlin.math.PI

private val DEFAULT_STROKE_CAP: StrokeCap = StrokeCap.Round
private val DEFAULT_POLYGON_LINE_STORK_SIZE: Dp = 1.dp
private val DEFAULT_POLYGON_STORK_SIZE: Dp = 1.dp
private const val DEFAULT_SCALAR_STEPS = 5


@Composable
fun RadarChart(
    radarValueMap: LinkedHashMap<String, Int>,
    polygonLineColor: Color,
    polygonColor: Color,
    polygonStrokeColor: Color,
    labelTextStyle: TextStyle,
    modifier: Modifier = Modifier,
    isPolygonStrokeShow: Boolean = true,
    scalarSteps: Int = DEFAULT_SCALAR_STEPS,
    polygonLineStrokeWidth: Float = DEFAULT_POLYGON_LINE_STORK_SIZE.toPx(),
    polygonStrokeWidth: Float = DEFAULT_POLYGON_STORK_SIZE.toPx(),
    polygonLineStrokeCap: StrokeCap = DEFAULT_STROKE_CAP
) {

    val vertexCount = radarValueMap.size

    if (vertexCount < 3)
        throw IllegalArgumentException("The minimum number of vertex count is 3.")

    // 정 다각형 꼭짓점을 찍기 위해 각도를 구한다.
    val angleBetweenLines = PI * 2 / vertexCount

    // Label 계산을 위한 필드
    val textMeasurer = rememberTextMeasurer(radarValueMap.size)
    val maxLabelWidth =
        measureMaxLabelWidth(radarValueMap.keys.toList(), labelTextStyle, textMeasurer)

    val labelPadding = 10.dp.toPx()


    Canvas(modifier = modifier) {
        val radius = (size.minDimension / 2) - maxLabelWidth - labelPadding
        for (index in 1..scalarSteps) {
            val radiusScalar = radius / scalarSteps * index
            // Polygon Line 그리기
            drawPolygonLine(
                radius = radiusScalar,
                angleBetweenLines = angleBetweenLines,
                vertexCount = vertexCount,
                color = polygonLineColor,
                strokeWidth = polygonLineStrokeWidth,
                cap = polygonLineStrokeCap
            )
        }

        // Polygon Label 그리기
        drawLabels(
            angleBetweenLines = angleBetweenLines,
            textMeasurer = textMeasurer,
            maxLabelWidth = maxLabelWidth,
            labels = radarValueMap.keys.toList(),
            style = labelTextStyle
        )

        // Polygon 그리기
        drawPolygon(
            angleBetweenLines = angleBetweenLines,
            radius = radius,
            stats = radarValueMap.values.toList(),
            polygonColor = polygonColor
        )

        // Polygon Stroke 그리기
        if (isPolygonStrokeShow) {
            drawPolygon(
                angleBetweenLines = angleBetweenLines,
                radius = radius,
                stats = radarValueMap.values.toList(),
                polygonColor = polygonStrokeColor,
                style = Stroke(polygonStrokeWidth)
            )
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
@DarkLightModePreview
fun DrawPolygonLinePreview2() {
    val map = linkedMapOf(
        "STR1" to 80,
        "STR2" to 76,
        "STR3" to 66,
        "STR4" to 43,
        "STR5" to 88,
        "STR6" to 53,
        "STR7" to 79,
        "STR8" to 100
    )

    SystemThemeSurface {
        RadarChart(
            map,
            Color.Gray,
            Color(0x6022A3A0),
            Color(0xFF22A3A0),
            MaterialTheme.typography.labelMedium.copy(color = MaterialTheme.colorScheme.secondary),
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .padding(30.dp)
        )

    }

}