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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp


@ExperimentalMaterial3Api
@Composable
fun PlayerInfoCard(
    value: String,
    modifier: Modifier = Modifier,
    title: String = "",
    valueTextStyle: TextStyle = MaterialTheme.typography.displaySmall,
    titleTextStyle: TextStyle = MaterialTheme.typography.titleMedium
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
                style = valueTextStyle,
                color = textColor
            )
            if (title.isNotEmpty()) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = title,
                    style = titleTextStyle,
                    color = textColor
                )
            }

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@DarkLightModePreview
@Composable
fun PlayerInfoColumnPreview() {
    SystemThemeSurface {
        Row(modifier = Modifier.fillMaxWidth()) {
            PlayerInfoCard("86", title = "오버롤", modifier = Modifier.weight(1f))
            PlayerInfoCard("31", title = "나이", modifier = Modifier.weight(1f))
            PlayerInfoCard("77", title = "등번호", modifier = Modifier.weight(1f))
        }
    }
}