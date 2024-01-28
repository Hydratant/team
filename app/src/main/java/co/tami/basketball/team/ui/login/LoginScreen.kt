package co.tami.basketball.team.ui.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import co.tami.basketball.team.R
import co.tami.basketball.team.ui.common.DarkLightModePreview
import co.tami.basketball.team.ui.common.SystemThemeSurface


@Composable
fun LoginScreen(
    modifier: Modifier = Modifier
) {
    val id = remember {
        mutableStateOf("")
    }

    val pwd = remember {
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
        PasswordOutlinedTextField(password = pwd.value, { password: String ->
            pwd.value = password
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
fun PasswordOutlinedTextField(
    password: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val label = "Password"
    val placeHolder = "Please Enter Your Password"

    BaseOutlinedTextField(
        password,
        label,
        placeHolder,
        KeyboardType.Password,
        onValueChange,
        modifier,
        visualTransformation = PasswordVisualTransformation(),
        trailingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_visibility_24dp),
                contentDescription = null
            )
        }
    )
}

@Composable
fun BaseOutlinedTextField(
    text: String,
    label: String,
    placeholder: String,
    keyboardType: KeyboardType,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable (() -> Unit)? = null
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
        onValueChange = onValueChange,
        visualTransformation = visualTransformation,
        trailingIcon = trailingIcon
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