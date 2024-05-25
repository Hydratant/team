@file:OptIn(ExperimentalMaterial3Api::class)

package co.tami.basketball.team.ui.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import co.tami.basketball.team.R
import co.tami.basketball.team.domain.entity.PlayerEntity
import co.tami.basketball.team.ui.common.DarkLightModePreview
import co.tami.basketball.team.ui.common.HorizontalSpacer
import co.tami.basketball.team.ui.common.PlayerInfoCard
import co.tami.basketball.team.ui.common.SystemThemeSurface
import co.tami.basketball.team.ui.common.VerticalSpacer


@Composable
fun PlayerListContainer(
    vm: PlayerListViewModel = hiltViewModel(),
    onPlayerClick: ((PlayerEntity) -> Unit)? = null
) {
    val players = vm.players.collectAsStateWithLifecycle()
    PlayerListScreen(
        players = players.value,
        onPlayerClick = onPlayerClick
    )
}

@Composable
fun PlayerListScreen(
    players: List<PlayerEntity>,
    onPlayerClick: ((PlayerEntity) -> Unit)? = null
) {

    LazyColumn(
        modifier = Modifier
            .padding(
                horizontal = 16.dp
            ),
    ) {
        items(players) { player: PlayerEntity ->
            PlayerItem(
                playerEntity = player,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
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

        Row(
            modifier = Modifier
                .padding(
                    vertical = 16.dp,
                    horizontal = 8.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(70.dp)
                    .aspectRatio(1f)
                    .clip(CircleShape)
                    .border(1.dp, Color.Gray, CircleShape),
                contentScale = ContentScale.Inside,
                painter = painterResource(id = R.drawable.luka_profile),
                contentDescription = null
            )
            HorizontalSpacer(size = 16.dp)

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = playerEntity.name,
                    style = MaterialTheme.typography.titleMedium
                )
                VerticalSpacer(size = 2.dp)
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = playerEntity.positions,
                    style = MaterialTheme.typography.titleLarge
                )
            }

            HorizontalSpacer(size = 4.dp)
            PlayerInfoCard(
                value = playerEntity.overRoll.toString(),
                modifier = Modifier.size(70.dp),
                valueTextStyle = MaterialTheme.typography.headlineMedium,
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
        image = "image",
        positions = "SG/PG",
        overRoll = 1,
        attributes = emptyList()
    )
    SystemThemeSurface {
        Box(
            modifier = Modifier
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
            name = "루카 돈치치",
            age = 20,
            jersey = 1,
            image = "image",
            positions = "SG/PG",
            overRoll = 34,
            attributes = emptyList()
        ),
        PlayerEntity(
            id = 2,
            name = "카이리 어빙",
            age = 20,
            jersey = 1,
            image = "image",
            positions = "SG/PG",
            overRoll = 88,
            attributes = emptyList()
        ),
        PlayerEntity(
            id = 3,
            name = "요키치",
            age = 20,
            jersey = 1,
            image = "image",
            positions = "SG/PG",
            overRoll = 97,
            attributes = emptyList()
        ),
        PlayerEntity(
            id = 4,
            name = "르브론 제임스",
            age = 20,
            jersey = 1,
            image = "image",
            positions = "SG/PG",
            overRoll = 76,
            attributes = emptyList()
        ),
    )
    SystemThemeSurface {
        PlayerListScreen(players = players)
    }
}