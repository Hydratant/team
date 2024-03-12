package co.tami.basketball.team.ui.common

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

private const val STAT_DEFAULT_MAX_VALUE = 100
private const val DEFAULT_STORK_SIZE = 40f
private val DEFAULT_UNSELECT_COLOR = Color.DarkGray

@Composable
fun DonutChart(
    value: Float,
    chartSize: Dp,
    selectColor: Color,
    modifier: Modifier = Modifier,
    strokeSize: Float = DEFAULT_STORK_SIZE,
    unSelectColor: Color = DEFAULT_UNSELECT_COLOR,
) {
    Canvas(
        modifier = modifier
            .size(chartSize)
    ) {
        // 빈 원
        drawArc(
            color = unSelectColor,
            startAngle = 0f,
            sweepAngle = 360f,
            useCenter = false,
            style = Stroke(strokeSize),
        )

        drawArc(
            color = selectColor,
            startAngle = 270f,
            sweepAngle = value * 360 / STAT_DEFAULT_MAX_VALUE,
            useCenter = false,
            style = Stroke(strokeSize),
        )
    }
}

@Composable
fun DonutChart(
    value: Float,
    chartSize: Dp,
    selectBrush: Brush,
    modifier: Modifier = Modifier,
    strokeSize: Float = DEFAULT_STORK_SIZE,
    unSelectColor: Color = DEFAULT_UNSELECT_COLOR,
) {
    Canvas(
        modifier = modifier
            .size(chartSize)
    ) {
        // 빈 원
        drawArc(
            color = unSelectColor,
            startAngle = 0f,
            sweepAngle = 360f,
            useCenter = false,
            style = Stroke(strokeSize),
        )

        drawArc(
            brush = selectBrush,
            startAngle = 270f,
            sweepAngle = value * 360 / STAT_DEFAULT_MAX_VALUE,
            useCenter = false,
            style = Stroke(strokeSize),
        )
    }
}


@DarkLightModePreview
@Composable
fun DonutChartPreview() {
    SystemThemeSurface {
        Box(
            modifier = Modifier
                .size(300.dp)
                .padding(30.dp)
        ) {
            DonutChart(
                80f,
                150.dp,
                Color.Green
            )
        }
    }
}

@DarkLightModePreview
@Composable
fun DonutChartBrushPreview() {
    SystemThemeSurface {
        Box(
            modifier = Modifier
                .size(300.dp)
                .padding(30.dp)
        ) {
            DonutChart(
                80f,
                150.dp,
                Brush.linearGradient(
                    colors = listOf(
                        Color(0xff63C6C4), Color(0xff97CA49)
                    ),
                    start = Offset.Zero,
                    end = Offset.Infinite,
                )
            )
        }
    }
}
