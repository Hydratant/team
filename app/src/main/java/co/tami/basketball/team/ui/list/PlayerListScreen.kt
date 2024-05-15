package co.tami.basketball.team.ui.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import co.tami.basketball.team.domain.entity.PlayerEntity
import co.tami.basketball.team.ui.common.DarkLightModePreview
import co.tami.basketball.team.ui.common.PlayerProfileImage
import co.tami.basketball.team.ui.common.SystemThemeSurface


@Composable
fun PlayerItem(
    playerEntity: PlayerEntity,
    modifier: Modifier = Modifier,
    onPlayerClick: ((PlayerEntity) -> Unit)? = null
) {
    Row(
        modifier = modifier
    ) {

        Column {
            PlayerProfileImage(profileImage = "")
            Text(text = playerEntity.name)
            Text(text = playerEntity.age.toString())
        }

    }

}


@DarkLightModePreview
@Composable
fun PlayerItemPreView() {
    val entity = PlayerEntity(
        id = 1,
        name = "name",
        age = 20,
        jersey = 1,
        positions = "positions",
        overRoll = 1,
        attributes = emptyList()
    )
    SystemThemeSurface {
        PlayerItem(entity)
    }
}