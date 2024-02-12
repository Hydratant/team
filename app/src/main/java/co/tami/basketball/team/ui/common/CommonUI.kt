package co.tami.basketball.team.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import co.tami.basketball.team.R
import co.tami.basketball.team.ui.theme.BasketballTeamTheme
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun SystemThemeSurface(
    content: @Composable () -> Unit
) {
    BasketballTeamTheme {
        Surface(color = MaterialTheme.colorScheme.background, content = content)
    }
}


@Composable
fun PlayerProfileImage(
    profileImage: String,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        modifier = modifier.clip(CircleShape),
        model = ImageRequest.Builder(LocalContext.current)
            .data(profileImage)
            .crossfade(true)
            .build(),
        placeholder = painterResource(id = R.drawable.ic_person_24dp),
        contentScale = ContentScale.Crop,
        contentDescription = null
    )
}

@DarkLightModePreview
@Composable
fun PlayerProfileImagePreview() {
    SystemThemeSurface {
        Box(
            modifier = Modifier
                .padding(20.dp)
        ) {
            PlayerProfileImage("")
        }
    }
}