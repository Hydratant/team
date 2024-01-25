package co.tami.basketball.team.ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import co.tami.basketball.team.ui.common.DarkLightModePreview
import co.tami.basketball.team.ui.common.SystemThemeSurface


@Composable
fun LoginScreen(
    modifier: Modifier = Modifier
) {
    val id = remember {
        mutableStateOf("")
    }
    Column(
        modifier = modifier.padding(
            horizontal = 16.dp,
            vertical = 20.dp
        )
    ) {
        EmailOutlinedTextField(email = id.value, { text: String ->
            id.value = text
        })
    }

}

@Composable
fun EmailOutlinedTextField(
    email: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val label = "Email"
    val placeHolder = "Enter Your Email"

    BaseOutlinedTextField(
        email,
        label,
        placeHolder,
        KeyboardType.Email,
        onValueChange,
        modifier
    )
}

@Composable
fun BaseOutlinedTextField(
    text: String,
    label: String,
    placeholder: String,
    keyboardType: KeyboardType,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth(),
        value = text,
        label = { Text(text = label) },
        placeholder = { Text(text = placeholder) },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        singleLine = true,
        onValueChange = onValueChange
    )
}


@DarkLightModePreview
@Composable
fun EmailOutlinedTextFieldPreview() {
    SystemThemeSurface {
        Column {
            EmailOutlinedTextField("ID", {})
        }
    }
}