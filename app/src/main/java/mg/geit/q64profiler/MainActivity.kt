package mg.geit.q64profiler

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.rememberNavController
import mg.geit.q64profiler.ui.theme.Q64ProfilerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Q64ProfilerTheme {
                Surface(color = Color.DarkGray) {
                    val navController = rememberNavController()
                    NavHostSetup(navController = navController)
                }
            }
        }
    }
}

@Composable
fun BackGroundImage(){
    Image(
        painter = painterResource(id = R.drawable.userinfobackground),
        contentDescription = "background",
        modifier = Modifier
            .fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}