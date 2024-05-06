package co.tami.basketball.team.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.tami.basketball.team.ui.chart.radar.RadarChart
import co.tami.basketball.team.ui.common.VerticalSpacer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerStatBottomSheet(
    title: String,
    stats: List<Int>,
    labels: List<String>,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    skipPartiallyExpanded: Boolean = false
) {
    // TODO : 3개 미만일 경우는 BarChart 를 사용하여 그리기
    if (stats.size < 3) return

    val bottomSheetState =
        rememberModalBottomSheetState(skipPartiallyExpanded = skipPartiallyExpanded)
    // Title
    // RadarChart
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = bottomSheetState,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = title)
            VerticalSpacer(size = 4.dp)
            StatRadarChart(stats, labels)
        }
    }
}

@Composable
fun StatRadarChart(
    stats: List<Int>,
    labels: List<String>
) {
    RadarChart(
        lineColor = MaterialTheme.colorScheme.primary,
        stats = stats,
        statColor = MaterialTheme.colorScheme.secondary,
        labels = labels,
        textStyle = MaterialTheme.typography.labelMedium.copy(color = MaterialTheme.colorScheme.secondary),
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
    )
}
