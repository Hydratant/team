package co.tami.basketball.team

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.tami.basketball.team.ui.detail.PlayerDetailContainer
import co.tami.basketball.team.ui.list.PlayerListContainer


@Composable
fun TeamApp() {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier,
    ) { contentPadding ->
        TeamNavHost(
            modifier = Modifier.padding(contentPadding),
            navController = navController
        )
    }


}

@Composable
fun TeamNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = TeamDestination.List.name
    ) {

        composable(TeamDestination.List.name) {
            PlayerListContainer(
                onPlayerClick = { navController.navigate(TeamDestination.Detail.name) }
            )
        }
        composable(TeamDestination.Detail.name) {
            PlayerDetailContainer(navController = navController)
        }
    }
}