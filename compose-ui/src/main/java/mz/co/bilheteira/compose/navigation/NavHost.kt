package mz.co.bilheteira.compose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import mz.co.bilheteira.compose.screens.details.RoverDetailsRoute
import mz.co.bilheteira.compose.screens.details.RoverDetailsScreen
import mz.co.bilheteira.compose.screens.list.RoverListRoute


private const val PHOTO_ID = "photoId"

sealed class NasaNavGraph(val route: String) {
    object RoverList : NasaNavGraph(route = "list")
    object RoverDetails : NasaNavGraph(route = "details/{$PHOTO_ID}")
}

@Composable
fun NasaNavHost(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = NasaNavGraph.RoverList.route) {
        composable(route = NasaNavGraph.RoverList.route) {
            RoverListRoute(onRoverPhotoClick = {
                val photoId = it.id
                //navHostController.navigate(NasaNavGraph.RoverDetails.route.plus("/$photoId"))
                navHostController.navigate("details/$photoId")
            })
        }

        composable(
            route = NasaNavGraph.RoverDetails.route,
            arguments = listOf(navArgument(PHOTO_ID){
                type = NavType.IntType
            })
        ) {
            RoverDetailsRoute(photoId = it.arguments?.getInt(PHOTO_ID) ?: 0)
        }
    }
}