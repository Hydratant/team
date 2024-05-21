package co.tami.basketball.team

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import co.tami.basketball.team.domain.entity.PlayerEntity
import co.tami.basketball.team.ui.detail.PlayerDetailScreen
import co.tami.basketball.team.ui.list.PlayerListScreen
import co.tami.basketball.team.ui.theme.BasketballTeamTheme
import dagger.hilt.android.AndroidEntryPoint

private val players = listOf(
    PlayerEntity(
        id = 1,
        name = "name",
        age = 20,
        jersey = 1,
        image = "image",
        positions = "SG/PG",
        overRoll = 1,
        attributes = emptyList()
    ),
    PlayerEntity(
        id = 2,
        name = "name",
        age = 20,
        jersey = 1,
        image = "image",
        positions = "SG/PG",
        overRoll = 1,
        attributes = emptyList()
    ),
    PlayerEntity(
        id = 3,
        name = "name",
        age = 20,
        jersey = 1,
        image = "image",
        positions = "SG/PG",
        overRoll = 1,
        attributes = emptyList()
    ),
    PlayerEntity(
        id = 4,
        name = "name",
        age = 20,
        jersey = 1,
        image = "image",
        positions = "SG/PG",
        overRoll = 1,
        attributes = emptyList()
    ),
)

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasketballTeamTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PlayerListScreen()
//                    PlayerDetailScreen()
                }
            }
        }
    }
}
