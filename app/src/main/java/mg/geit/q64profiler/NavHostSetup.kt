package mg.geit.q64profiler

import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Composable
fun NavHostSetup(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("user_form") { UserFormScreen(navController) }
        composable(
            "quiz/username={username}&email={email}&phoneNumber={phoneNumber}",
            arguments = listOf(
                navArgument("username") { type = NavType.StringType },
                navArgument("email") { type = NavType.StringType },
                navArgument("phoneNumber") { type = NavType.StringType }
            )
        ) { backStackEntry ->
                val username = backStackEntry.arguments?.getString("username")
                val email = backStackEntry.arguments?.getString("email")
                val phoneNumber = backStackEntry.arguments?.getString("phoneNumber")
            QuizScreen(navController = navController , username = username, email = email, phoneNumber = phoneNumber)
        }
        composable(
            route = "result/{results}&username={username}",
            arguments = listOf(
                navArgument("username") { type = NavType.StringType },
                navArgument("results") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username")
            val resultsJson = backStackEntry.arguments?.getString("results")
            val results: Map<String, Int> = Gson().fromJson(resultsJson, object : TypeToken<Map<String, Int>>() {}.type)
            ResultScreen(navController = navController, results = results, username = username)
        }
    }
}
