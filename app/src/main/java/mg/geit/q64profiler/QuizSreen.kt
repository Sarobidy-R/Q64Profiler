package mg.geit.q64profiler

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import mg.geit.q64profiler.utils.calculateResults
import mg.geit.q64profiler.utils.combineAndSortQuestions
import mg.geit.q64profiler.utils.getQuestionsFromJson

@Composable
fun QuizScreen(navController: NavController) {
    val context = LocalContext.current
    val questions = getQuestionsFromJson(context, "Profils.json")
    val allQuestions = combineAndSortQuestions(questions)
    val totalQuestions = allQuestions.size

    var questionIndex by remember { mutableStateOf(0) }
    val userAnswers = remember { mutableStateMapOf<String, MutableList<Int>>() }
    var results by remember { mutableStateOf<Map<String, Int>?>(null) }


    Image(
        painter = painterResource(id = R.drawable.userinfobackground),
        contentDescription = null,
        modifier = Modifier
            .fillMaxSize(),
        contentScale = ContentScale.Crop
    )


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (results == null) {
            val currentQuestion = allQuestions[questionIndex]
            val profile = currentQuestion.first
            val questionText = currentQuestion.second

            Spacer(modifier = Modifier.height(16.dp))
            Text(text = questionText)
            Spacer(modifier = Modifier.height(16.dp))

            var answer by remember { mutableStateOf(3) }
            Row {
                (1..5).forEach {
                    RadioButton(
                        selected = answer == it,
                        onClick = { answer = it }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }

            Row(
                modifier = Modifier.padding(12.dp),
            ) {
                Button(
                    modifier = Modifier.padding(20.dp),
                    onClick = {
                        if (questionIndex > 0) {
                            questionIndex--
                        }
                    },
                    enabled = questionIndex > 0
                ) {
                    Text(text = "Retour")
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(modifier = Modifier.padding(20.dp),
                    onClick = {
                        userAnswers.getOrPut(profile) { mutableListOf() }.add(answer)
                        if (questionIndex < totalQuestions - 1) {
                            questionIndex++
                        } else {
                            results = calculateResults(userAnswers)
                        }
                    }) {
                    Text(text = "Suivant")
                }
            }
        } else {
            navController.navigate("result")
        }
    }


}
@Preview
@Composable
fun QuizScreenPreview() {
    QuizScreen(navController = NavController(LocalContext.current))
}