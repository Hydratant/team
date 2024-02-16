package co.tami.basketball.team.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import co.tami.basketball.team.ui.common.DarkLightModePreview
import co.tami.basketball.team.ui.common.PlayerProfileImage
import co.tami.basketball.team.ui.common.SystemThemeSurface


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
            PlayerInfoColumn("31", "AGE", modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(8.dp))
            PlayerInfoColumn("77", "JERSEY", modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(8.dp))
            PlayerInfoColumn("2,3", "POSITION", modifier = Modifier.weight(1f))
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
fun PlayerInfoColumn(
    value: String,
    title: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .aspectRatio(1f)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.background)
            .shadow(
                3.dp,
                ambientColor = MaterialTheme.colorScheme.onBackground,
                spotColor = MaterialTheme.colorScheme.onBackground

            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge
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
fun PlayerInfoColumnPreview() {
    SystemThemeSurface {
        Row(modifier = Modifier.fillMaxWidth()) {
            PlayerInfoColumn("13", "AGE", modifier = Modifier.weight(1f))
            PlayerInfoColumn("13", "AGE", modifier = Modifier.weight(1f))
            PlayerInfoColumn("13", "AGE", modifier = Modifier.weight(1f))
        }

    }
}