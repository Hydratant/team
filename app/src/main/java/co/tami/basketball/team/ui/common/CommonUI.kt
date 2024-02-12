package co.tami.basketball.team.ui.common

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import co.tami.basketball.team.ui.theme.BasketballTeamTheme

@Composable
fun SystemThemeSurface(
    content: @Composable () -> Unit
) {
    BasketballTeamTheme {
        Surface(color = MaterialTheme.colorScheme.background, content = content)
    }
}
