package co.tami.basketball.team.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import co.tami.basketball.team.R
import co.tami.basketball.team.ui.common.DarkLightModePreview
import co.tami.basketball.team.ui.chart.donut.DonutChart
import co.tami.basketball.team.ui.chart.radar.DrawLines
import co.tami.basketball.team.ui.common.SystemThemeSurface
import co.tami.basketball.team.ui.common.VerticalSpacer


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerDetailScreen() {
    Column {

        DrawLines(modifier = Modifier.size(300.dp),4)
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
                    name = "루카 돈치치",
                    modifier = Modifier.background(Color.Black.copy(alpha = 0.5f))
                )

                VerticalSpacer(size = 4.dp)
                Text(
                    modifier = Modifier.background(Color.Black.copy(alpha = 0.5f)),
                    text = "PG/SG",
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
            PlayerInfoCard("89", "OVR", modifier = Modifier.weight(1f))
            PlayerInfoCard("31", "AGE", modifier = Modifier.weight(1f))
            PlayerInfoCard("77", "Jersey", modifier = Modifier.weight(1f))
        }

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 24.dp
                )
        )


        Column(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                PlayerStats(
                    stats = 88,
                    statsTitle = "Outside\nScoring",
                    modifier = Modifier.weight(1f)
                )
                PlayerStats(
                    stats = 86,
                    statsTitle = "Inside\nScoring",
                    modifier = Modifier.weight(1f)
                )
                PlayerStats(stats = 69, statsTitle = "Defending", modifier = Modifier.weight(1f))
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                PlayerStats(stats = 88, statsTitle = "Athleticism", modifier = Modifier.weight(1f))
                PlayerStats(stats = 86, statsTitle = "Playmaking", modifier = Modifier.weight(1f))
                PlayerStats(stats = 69, statsTitle = "Rebounding", modifier = Modifier.weight(1f))
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

@ExperimentalMaterial3Api
@Composable
fun PlayerInfoCard(
    value: String,
    title: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .aspectRatio(1f)
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),

        ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}


@Composable
fun PlayerStats(
    stats: Int,
    statsTitle: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box {
            DonutChart(
                modifier = Modifier.align(Alignment.Center),
                value = stats.toFloat(),
                selectColor = MaterialTheme.colorScheme.tertiary,
                unSelectColor = MaterialTheme.colorScheme.tertiaryContainer
            )
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = stats.toString(),
                style = MaterialTheme.typography.headlineMedium
            )
        }
        VerticalSpacer(size = 8.dp)
        Text(
            text = statsTitle,
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

@OptIn(ExperimentalMaterial3Api::class)
@DarkLightModePreview
@Composable
fun PlayerInfoColumnPreview() {
    SystemThemeSurface {
        Row(modifier = Modifier.fillMaxWidth()) {
            PlayerInfoCard("77", "OVR", modifier = Modifier.weight(1f))
            PlayerInfoCard("31", "AGE", modifier = Modifier.weight(1f))
            PlayerInfoCard("77", "Jersey", modifier = Modifier.weight(1f))
        }

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
            PlayerStats(stats = 88, statsTitle = "OutSide\nScoring", modifier = Modifier.weight(1f))
            PlayerStats(stats = 55, statsTitle = "OutSide\nScoring", modifier = Modifier.weight(1f))
            PlayerStats(stats = 78, statsTitle = "OutSide\nScoring", modifier = Modifier.weight(1f))
        }
    }
}
