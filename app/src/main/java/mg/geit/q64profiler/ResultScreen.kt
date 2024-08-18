package mg.geit.q64profiler

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import mg.geit.q64profiler.utils.getProfileDescription
import mg.geit.q64profiler.utils.getProfileImageResource

@Composable
fun ResultScreen(navController: NavController, results: Map<String, Int>?) {
    val top3Profiles = results!!.entries.sortedByDescending { it.value }.take(3)
    val lowestProfile = results.entries.minByOrNull { it.value }

    var selectedProfile by remember { mutableStateOf<String?>(null) }

    BackGroundImage()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Résultats:", color = Color.White)

        top3Profiles.forEach { (profile, score) ->
            ProfileItem(
                profile = profile,
                score = score,
                onClick = { selectedProfile = profile }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        lowestProfile?.let {
            Spacer(modifier = Modifier.height(32.dp))
            ProfileItem(
                profile = it.key,
                score = it.value,
                onClick = { selectedProfile = it.key }
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = { navController.navigate("quiz") }) {
            Text(text = "Recommencer")
        }
    }

    selectedProfile?.let { profile ->
        ProfileDescriptionDialog(profileName = profile, onDismiss = { selectedProfile = null })
    }
}

@Composable
fun ProfileItem(profile: String, score: Int, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.clickable { onClick() }
    ) {
        Image(
            painter = painterResource(id = getProfileImageResource(profile)),
            contentDescription = profile,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clip(shape = androidx.compose.foundation.shape.CircleShape)
                .fillMaxSize()
                .height(100.dp)
        )
        Text(text = "$profile: $score", color = Color.White)
    }
}

@Composable
fun ProfileDescriptionDialog(profileName: String, onDismiss: () -> Unit) {
    val description = getProfileDescription(profileName)
    AlertDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            Button(onClick = { onDismiss() }) {
                Text("OK")
            }
        },
        title = { Text(text = profileName) },
        text = { Text(text = description) }
    )
}



@Preview(showBackground = true)
@Composable
fun ResultScreenPreview() {
    val sampleResults = mapOf(
        "leader" to 10,
        "épicurien" to 15,
        "bienfaiteur" to 5,
        "visionnaire" to 8
    )
    val mockNavController = rememberNavController()
    ResultScreen(navController = mockNavController, results = sampleResults)
}
