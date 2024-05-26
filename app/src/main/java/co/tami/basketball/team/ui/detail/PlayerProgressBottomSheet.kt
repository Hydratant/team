package co.tami.basketball.team.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import co.tami.basketball.team.ui.chart.progress.ProgressIndicator
import co.tami.basketball.team.ui.common.VerticalSpacer


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerProgressStatBottomSheet(
    title: String,
    stats: List<Int>,
    labels: List<String>,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    skipPartiallyExpanded: Boolean = false
) {
    val bottomSheetState =
        rememberModalBottomSheetState(skipPartiallyExpanded = skipPartiallyExpanded)

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

            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
            )

            stats.forEachIndexed { index: Int, stat: Int ->
                Text(text = labels[index])
                VerticalSpacer(size = 4.dp)
                ProgressIndicator(
                    progress = stat,
                    backgroundIndicatorColor = Color.Gray,
                    indicatorColor = Color.Black
                )
            }
        }
    }
}

