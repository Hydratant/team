package co.tami.basketball.team.ui.chart.progress

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import co.tami.basketball.team.ui.common.DarkLightModePreview
import co.tami.basketball.team.ui.common.SystemThemeSurface

@Composable
fun ProgressIndicator(
    progress: Int,
    backgroundIndicatorColor: Color,
    indicatorColor: Color,
    modifier: Modifier = Modifier
) {
    val startOffset = Offset(0f, 0f)

    Canvas(modifier = modifier) {
        val strokeWidth = size.height * 2

        // drawBackgroundIndicator
        drawLine(
            color = backgroundIndicatorColor,
            start = startOffset,
            end = Offset(x = size.width, y = 0f),
            strokeWidth = strokeWidth
        )

        val progressPercent = (progress / 100f) * size.width
        drawLine(
            color = indicatorColor,
            start = startOffset,
            end = Offset(x = progressPercent, y = 0f),
            strokeWidth = strokeWidth
        )
    }
}

@DarkLightModePreview
@Composable
fun DonutChartPreview() {
    SystemThemeSurface {
        ProgressIndicator(
            88,
            Color.LightGray,
            Color.Gray,
            modifier = Modifier
                .fillMaxWidth()
                .height(15.dp)
        )
    }
}