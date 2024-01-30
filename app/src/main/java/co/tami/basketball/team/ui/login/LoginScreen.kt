package co.tami.basketball.team.ui.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import co.tami.basketball.team.R
import co.tami.basketball.team.ui.common.DarkLightModePreview
import co.tami.basketball.team.ui.common.SystemThemeSurface


@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    vm: LoginViewModel = hiltViewModel()
) {
    val email = vm.email.collectAsState()
    val password = vm.password.collectAsState()
    val isVisibilityPassword = vm.isVisibilityPassword.collectAsState()

    Column(
        modifier = modifier.padding(
            horizontal = 16.dp,
            vertical = 20.dp
        )
    ) {
        EmailOutlinedTextField(
            email = email.value,
            onValueChange = { email: String ->
                vm.onEmailValueChange(email)
            })
        PasswordOutlinedTextField(
            password = password.value,
            isVisibilityPassword = isVisibilityPassword.value,
            onVisibilityClick = { isVisibilityPassword ->
                vm.isVisibilityPassword(isVisibilityPassword)
            },
            onValueChange = { password: String ->
                vm.onPasswordValueChange(password)
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
    isVisibilityPassword: Boolean,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    onVisibilityClick: ((Boolean) -> Unit)
) {
    val label = "Password"
    val placeHolder = "Please Enter Your Password"

    val iconResId =
        if (isVisibilityPassword)
            R.drawable.ic_visibility_off_24dp
        else
            R.drawable.ic_visibility_24dp


    val visualTransformation =
        if (isVisibilityPassword)
            VisualTransformation.None
        else
            PasswordVisualTransformation()


    BaseOutlinedTextField(
        password,
        label,
        placeHolder,
        KeyboardType.Password,
        onValueChange,
        modifier,
        visualTransformation = visualTransformation,
        trailingIcon = {
            Icon(
                modifier = Modifier.clickable {
                    onVisibilityClick.invoke(isVisibilityPassword.not())
                },
                imageVector = ImageVector.vectorResource(id = iconResId),
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