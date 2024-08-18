package mg.geit.q64profiler

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ResultScreen(navController: NavController, results: Map<String, Int>) {
    val top3Profiles = results.entries.sortedByDescending { it.value }.take(3)
    val lowestProfile = results.entries.minByOrNull { it.value }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Résultats:", color = Color.White)

        top3Profiles.forEach { (profile, score) ->
            Text(text = "$profile: $score", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        lowestProfile?.let {
            Text(text = "Profil avec le score le plus bas: ${it.key} :: ${it.value}", color = Color.White)
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = { navController.navigate("quiz") }) {
            Text(text = "Recommencer")
        }
    }
}
