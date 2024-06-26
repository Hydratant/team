package co.tami.basketball.team.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import co.tami.basketball.team.R
import co.tami.basketball.team.domain.entity.PlayerAttributeEntity
import co.tami.basketball.team.ui.chart.donut.DonutChart
import co.tami.basketball.team.ui.common.DarkLightModePreview
import co.tami.basketball.team.ui.common.PlayerInfoCard
import co.tami.basketball.team.ui.common.SystemThemeSurface
import co.tami.basketball.team.ui.common.VerticalSpacer
import timber.log.Timber

@Composable
fun PlayerDetailContainer(
    navController: NavHostController,
    vm: PlayerDetailViewModel = hiltViewModel()
) {
    // Collect State
    val name: State<String> = vm.name.collectAsStateWithLifecycle()
    val position: State<String> = vm.position.collectAsStateWithLifecycle()
    val age: State<String> = vm.age.collectAsStateWithLifecycle()
    val jersey: State<String> = vm.jersey.collectAsStateWithLifecycle()
    val overRoll: State<String> = vm.overRoll.collectAsStateWithLifecycle()
    val attributes: State<List<PlayerAttributeEntity>> = vm.attributes.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = vm.event) {
        vm.event.collect { event: PlayerDetailViewModel.Event ->
            when (event) {
                is PlayerDetailViewModel.Event.Finish -> {
                    Timber.i("Finish Call")
                    // Navigation Back
                    navController.popBackStack()
                }
            }
        }
    }

    PlayerDetailScreen(
        name = name.value,
        position = position.value,
        age = age.value,
        jersey = jersey.value,
        overRoll = overRoll.value,
        attributes = attributes.value
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerDetailScreen(
    name: String,
    position: String,
    age: String,
    jersey: String,
    overRoll: String,
    attributes: List<PlayerAttributeEntity>,

    modifier: Modifier = Modifier,
    vm: PlayerDetailViewModel = hiltViewModel()
) {
    // BottomSheet
    val bottomSheetEvent = vm.bottomSheetEvent.collectAsStateWithLifecycle()
    when (bottomSheetEvent.value) {
        is PlayerDetailViewModel.BottomSheetEvent.Show -> {

            val item = (bottomSheetEvent.value as PlayerDetailViewModel.BottomSheetEvent.Show).item
            if (item.value.size > 2) {
                PlayerStatBottomSheet(
                    title = item.title,
                    stats = item.value.values.toList(),
                    labels = item.value.keys.toList(),
                    onDismissRequest = { vm.hideBottomSheet() },
                    skipPartiallyExpanded = true
                )
            } else {
                PlayerProgressStatBottomSheet(
                    title = item.title,
                    stats = item.value.values.toList(),
                    labels = item.value.keys.toList(),
                    onDismissRequest = { vm.hideBottomSheet() },
                    skipPartiallyExpanded = true
                )
            }
        }

        PlayerDetailViewModel.BottomSheetEvent.Hide -> Unit
    }


    Column(modifier = modifier) {

        Box {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f),
                painter = painterResource(id = R.drawable.ic_luka),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(bottom = 24.dp, start = 24.dp)
            ) {
                PlayerNameText(
                    name = name,
                    modifier = Modifier.background(Color.Black.copy(alpha = 0.5f))
                )

                VerticalSpacer(size = 4.dp)
                Text(
                    modifier = Modifier.background(Color.Black.copy(alpha = 0.5f)),
                    text = position,
                    style = MaterialTheme.typography.headlineSmall
                )
            }

        }


        VerticalSpacer(size = 16.dp)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            PlayerInfoCard(overRoll, title = "능력치", modifier = Modifier.weight(1f))
            PlayerInfoCard(age, title = "나이", modifier = Modifier.weight(1f))
            PlayerInfoCard(jersey, title = "등번호", modifier = Modifier.weight(1f))
        }

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 24.dp
                )
        )

        LazyVerticalGrid(
            modifier = Modifier.padding(horizontal = 16.dp),
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(32.dp),
            horizontalArrangement = Arrangement.spacedBy(40.dp)
        ) {
            items(attributes) { item: PlayerAttributeEntity ->
                PlayerStats(
                    item = item,
                    onStatClick = { entity: PlayerAttributeEntity ->
                        vm.showBottomSheet(entity)
                    }
                )
            }
        }
    }
}


@Composable
fun PlayerNameText(
    name: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = name,
        style = MaterialTheme.typography.headlineMedium
    )
}


@Composable
fun PlayerStats(
    item: PlayerAttributeEntity,
    modifier: Modifier = Modifier,
    onStatClick: ((item: PlayerAttributeEntity) -> Unit)? = null
) {
    Column(
        modifier = modifier
            .clickable { onStatClick?.invoke(item) },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box {
            DonutChart(
                modifier = Modifier.align(Alignment.Center),
                value = item.average.toFloat(),
                selectColor = MaterialTheme.colorScheme.primary,
                unSelectColor = MaterialTheme.colorScheme.surfaceContainerHighest
            )
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = item.average.toString(),
                style = MaterialTheme.typography.headlineMedium
            )
        }
        VerticalSpacer(size = 8.dp)
        Text(
            text = item.title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }

}

@DarkLightModePreview
@Composable
fun DetailNamePreview() {
    SystemThemeSurface {
        PlayerNameText("루카 돈치치")
    }
}


@DarkLightModePreview
@Composable
fun PlayerStatsPreview() {
    SystemThemeSurface {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            PlayerStats(
                PlayerAttributeEntity(
                    "슛", 89, mapOf("2P" to 77, "Dunk" to 77),
                ),
                modifier = Modifier.weight(1f)
            )
            PlayerStats(
                PlayerAttributeEntity(
                    "드리블", 89, mapOf("2P" to 77, "Dunk" to 77),
                ),
                modifier = Modifier.weight(1f)
            )
            PlayerStats(
                PlayerAttributeEntity(
                    "패스", 89, mapOf("2P" to 77, "Dunk" to 77),
                ),
                modifier = Modifier.weight(1f)
            )
        }
    }
}
