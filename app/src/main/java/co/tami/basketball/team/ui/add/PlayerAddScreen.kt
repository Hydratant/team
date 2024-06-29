package co.tami.basketball.team.ui.add

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import co.tami.basketball.team.R
import co.tami.basketball.team.ui.common.BackImage
import co.tami.basketball.team.ui.common.DarkLightModePreview
import co.tami.basketball.team.ui.common.SystemThemeSurface
import co.tami.basketball.team.ui.common.VerticalSpacer

@Composable
fun PlayerScreen() {


}

@Composable
fun PlayerNameAddColumn() {
    Column {
        BackImage(
            modifier = Modifier
                .padding(start = 8.dp, top = 24.dp)
        )
        VerticalSpacer(size = 24.dp)
        AddInfoText(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            value = "안녕하세요!\n등록 하려는 선수 이름을 입력해 주세요."
        )
        VerticalSpacer(size = 24.dp)
        PlayerNameTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            value = "",
            onValueChange = {}
        )

    }
}

@Composable
fun AddInfoText(
    value: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = value,
        style = MaterialTheme.typography.headlineSmall
    )
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
fun PlayerNameAddColumnPreview() {
    SystemThemeSurface {
        Box(modifier = Modifier.fillMaxSize()) {
            PlayerNameAddColumn()
        }
    }
}

@DarkLightModePreview
@Composable
fun AddInfoTextPreview() {
    SystemThemeSurface {
        AddInfoText(
            modifier = Modifier.fillMaxWidth(),
            value = "안녕하세요!\n등록 하려는 선수 이름을 입력해 주세요."
        )
    }
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
