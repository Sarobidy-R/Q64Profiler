package mg.geit.q64profiler

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.gson.Gson
import mg.geit.q64profiler.utils.calculateResults
import mg.geit.q64profiler.utils.combineAndSortQuestions
import mg.geit.q64profiler.utils.getQuestionsFromJson


@Composable
fun QuizScreen(navController: NavController) {
    val context = LocalContext.current
    val questionsByProfile = getQuestionsFromJson(context, "Profils.json")
    val allQuestions = combineAndSortQuestions(questionsByProfile)
    val totalQuestions = allQuestions.size

    var questionIndex by remember { mutableStateOf(0) }
    val answersByProfile = remember { mutableStateMapOf<String, MutableList<Int>>() }
    var results by remember { mutableStateOf<Map<String, Int>?>(null) }

    BackGroundImage()

    Column(
        modifier = Modifier
            .background(color = Color.White.copy(alpha = 0.08f))
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (results == null) {
        if (questionIndex < totalQuestions) {
            val (profile, questionText) = allQuestions[questionIndex]

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = questionText,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.scrim,
                modifier = Modifier.padding(8.dp)
            )

            Spacer(modifier = Modifier.height(30.dp))

            val options = listOf(
                "Totalement en accord \uD83D\uDE07",
                "En accord ☺\uFE0F",
                "Neutre \uD83D\uDE36 ",
                "En désaccord \uD83D\uDE11",
                "Totalement en désaccord \uD83D\uDE12"
            )

            var selectedOption by remember { mutableStateOf(3) } // Default to "Neutre"

            Column {
                options.forEachIndexed { index, option ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(vertical = 4.dp)
                            .clickable { selectedOption = index + 1}
                    ) {
                        RadioButton(
                            selected = selectedOption == index + 1,
                            onClick = { selectedOption = index + 1 },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = MaterialTheme.colorScheme.primary,
                                unselectedColor = MaterialTheme.colorScheme.onBackground
                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = option,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = { questionIndex = (questionIndex - 1).coerceAtLeast(0) },
                    enabled = questionIndex > 0,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary
                    )
                ) {
                    Text(text = "Retour")
                }

                Button(
                    onClick = {
                        answersByProfile.getOrPut(profile) { mutableListOf() }.add(selectedOption)
                        if (questionIndex < totalQuestions - 1) {
                            questionIndex++
                        } else {
                            results = calculateResults(answersByProfile)
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(text = "Suivant")
                }
            }
        }
    } else {
        val resultsJson = Gson().toJson(results)
        navController.navigate("result/$resultsJson")
        }
    }
}
@Preview
@Composable
fun QuizScreenPreview() {
    QuizScreen(navController = NavController(LocalContext.current))
}
