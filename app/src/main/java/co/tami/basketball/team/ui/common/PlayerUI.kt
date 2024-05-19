package co.tami.basketball.team.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@ExperimentalMaterial3Api
@Composable
fun PlayerInfoCard(
    value: String,
    title: String,
    modifier: Modifier = Modifier
) {
    val textColor = MaterialTheme.colorScheme.onSurface
    Card(
        modifier = modifier
            .aspectRatio(1f)
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),

        ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceBright),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.displaySmall,
                color = textColor
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = textColor
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@DarkLightModePreview
@Composable
fun PlayerInfoColumnPreview() {
    SystemThemeSurface {
        Row(modifier = Modifier.fillMaxWidth()) {
            PlayerInfoCard("86", "오버롤", modifier = Modifier.weight(1f))
            PlayerInfoCard("31", "나이", modifier = Modifier.weight(1f))
            PlayerInfoCard("77", "등번호", modifier = Modifier.weight(1f))
        }
    }
}