package co.tami.basketball.team.ui.detail

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import co.tami.basketball.team.ui.common.DarkLightModePreview
import co.tami.basketball.team.ui.common.SystemThemeSurface


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

@DarkLightModePreview
@Composable
fun DetailNamePreview() {
    SystemThemeSurface {
        PlayerNameText("루카 돈치치")
    }
}