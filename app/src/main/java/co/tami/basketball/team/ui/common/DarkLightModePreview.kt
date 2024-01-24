package co.tami.basketball.team.ui.common

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

private const val LIGHT_MODE = "Light Mode"
private const val DARK_MODE = "Dark Mode"

@Preview(name = LIGHT_MODE)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = DARK_MODE
)
annotation class DarkLightModePreview