@file:Suppress("SpellCheckingInspection")

package co.tami.basketball.team.ui.chart.radar

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import co.tami.basketball.team.ext.toPx
import co.tami.basketball.team.ui.common.DarkLightModePreview
import co.tami.basketball.team.ui.common.SystemThemeSurface


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

        // Polygon Label 그리기
        drawLabels(
            vertexCount = vertexCount,
            textMeasurer = textMeasurer,
            labels = radarValueMap.keys.toList(),
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
        "Party7" to 88,
        "Party9" to 53,
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