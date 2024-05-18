package co.tami.basketball.team.ui.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import co.tami.basketball.team.R
import co.tami.basketball.team.domain.entity.PlayerEntity
import co.tami.basketball.team.ui.common.DarkLightModePreview
import co.tami.basketball.team.ui.common.SystemThemeSurface
import co.tami.basketball.team.ui.common.VerticalSpacer


@Composable
fun PlayerListScreen(
    players: List<PlayerEntity>,
    onPlayerClick: ((PlayerEntity) -> Unit)? = null
) {
    LazyVerticalGrid(
        modifier = Modifier.padding(
            horizontal = 16.dp
        ),
        columns = GridCells.Fixed(3)
    ) {
        items(players) { player: PlayerEntity ->
            PlayerItem(
                playerEntity = player,
                modifier = Modifier.padding(8.dp),
                onPlayerClick = onPlayerClick
            )
        }
    }
}


@Composable
fun PlayerItem(
    playerEntity: PlayerEntity,
    modifier: Modifier = Modifier,
    onPlayerClick: ((PlayerEntity) -> Unit)? = null
) {

    Card(modifier = modifier
        .clickable {
            onPlayerClick?.invoke(playerEntity)
        }) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            Image(
                modifier = Modifier
                    .size(60.dp)
                    .aspectRatio(1f)
                    .clip(CircleShape)
                    .border(1.dp, Color.Gray, CircleShape),
                contentScale = ContentScale.Inside,
                painter = painterResource(id = R.drawable.luka_profile),
                contentDescription = null
            )
            VerticalSpacer(size = 8.dp)
            Text(
                text = playerEntity.name,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = playerEntity.positions,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}


@DarkLightModePreview
@Composable
fun PlayerItemPreview() {
    val entity = PlayerEntity(
        id = 1,
        name = "name",
        age = 20,
        jersey = 1,
        positions = "SG/PG",
        overRoll = 1,
        attributes = emptyList()
    )
    SystemThemeSurface {
        Box(
            modifier = Modifier
                .width(100.dp)
                .height(150.dp)
                .padding(8.dp)
        ) {
            PlayerItem(entity)
        }
    }
}

@DarkLightModePreview
@Composable
fun PlayerListScreenPreview() {
    val players = listOf(
        PlayerEntity(
            id = 1,
            name = "name",
            age = 20,
            jersey = 1,
            positions = "SG/PG",
            overRoll = 1,
            attributes = emptyList()
        ),
        PlayerEntity(
            id = 2,
            name = "name",
            age = 20,
            jersey = 1,
            positions = "SG/PG",
            overRoll = 1,
            attributes = emptyList()
        ),
        PlayerEntity(
            id = 3,
            name = "name",
            age = 20,
            jersey = 1,
            positions = "SG/PG",
            overRoll = 1,
            attributes = emptyList()
        ),
        PlayerEntity(
            id = 4,
            name = "name",
            age = 20,
            jersey = 1,
            positions = "SG/PG",
            overRoll = 1,
            attributes = emptyList()
        ),
    )
    SystemThemeSurface {
        PlayerListScreen(players = players)
    }
}