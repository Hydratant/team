package co.tami.basketball.team.ui.add

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import co.tami.basketball.team.R
import co.tami.basketball.team.ui.common.DarkLightModePreview
import co.tami.basketball.team.ui.common.SystemThemeSurface

@Composable
fun PlayerScreen() {

}

@Composable
fun PlayerNameTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        placeholder = { Text(text = stringResource(id = R.string.player_name_placeholder)) },
        onValueChange = onValueChange
    )
}

@DarkLightModePreview
@Composable
fun PlayerNameTextFieldPreview() {
    SystemThemeSurface {
        PlayerNameTextField(
            value = "",
            onValueChange = {}
        )
    }
}