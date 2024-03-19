package co.tami.basketball.team.ui.chart.donut

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import co.tami.basketball.team.ext.toDp
import co.tami.basketball.team.ext.toPx
import co.tami.basketball.team.ui.common.DarkLightModePreview
import co.tami.basketball.team.ui.common.SystemThemeSurface

private const val STAT_DEFAULT_MAX_VALUE = 100
private val DEFAULT_STORK_SIZE = 16.dp
private val DEFAULT_UNSELECT_COLOR = Color.DarkGray

@Composable
fun DonutChart(
    value: Float,
    selectColor: Color,
    modifier: Modifier = Modifier,
    strokeSize: Float = DEFAULT_STORK_SIZE.toPx(),
    unSelectColor: Color = DEFAULT_UNSELECT_COLOR,
) {
    val chartPadding = (strokeSize / 2).toInt().toDp()
    Canvas(
        modifier = modifier
            .aspectRatio(1f)
            .fillMaxSize()
            .padding(chartPadding)
    ) {
        // 빈 원
        drawCircle(
            color = unSelectColor,
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
    selectBrush: Brush,
    modifier: Modifier = Modifier,
    strokeSize: Float = DEFAULT_STORK_SIZE.toPx(),
    unSelectColor: Color = DEFAULT_UNSELECT_COLOR,
) {
    val chartPadding = (strokeSize / 2).toInt().toDp()
    Canvas(
        modifier = modifier
            .aspectRatio(1f)
            .fillMaxSize()
            .padding(chartPadding)
    ) {
        // 빈 원
        drawCircle(
            color = unSelectColor,
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
        DonutChart(
            80f,
            Color.Green
        )
    }
}

@DarkLightModePreview
@Composable
fun DonutChartBrushPreview() {
    SystemThemeSurface {
        DonutChart(
            80f,
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
