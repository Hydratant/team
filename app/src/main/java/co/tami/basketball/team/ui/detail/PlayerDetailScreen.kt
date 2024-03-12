package co.tami.basketball.team.ui.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.tami.basketball.team.ui.common.DarkLightModePreview
import co.tami.basketball.team.ui.common.SystemThemeSurface


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerDetailScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

//        PlayerProfileImage(profileImage = "")
        PlayerNameText(name = "루카 돈치치")

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            PlayerInfoCard("31", "AGE", modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(8.dp))
            PlayerInfoCard("77", "JERSEY", modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(8.dp))
            PlayerInfoCard("2,3", "POSITION", modifier = Modifier.weight(1f))
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
            .padding(16.dp),
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
                style = MaterialTheme.typography.displaySmall
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge
            )
        }
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
            PlayerInfoCard("13", "AGE", modifier = Modifier.weight(1f))
            PlayerInfoCard("77", "Jersey", modifier = Modifier.weight(1f))
        }

    }
}