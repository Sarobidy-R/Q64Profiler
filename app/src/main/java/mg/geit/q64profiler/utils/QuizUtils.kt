package mg.geit.q64profiler.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

fun combineAndSortQuestions(questions: Map<String, List<String>>): List<Pair<String, String>> {
    return questions.flatMap { (profile, questionList) ->
        questionList.map { question ->
            profile to question
        }
    }.sortedBy { (_, question) ->
        val matchResult = Regex("Q(\\d+)").find(question)
        matchResult?.groupValues?.get(1)?.toInt() ?: 0
    }
}

fun loadJSONFromFile(context: Context, fileName: String): String? {
    return try {
        val inputStream = context.assets.open(fileName)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        String(buffer, Charsets.UTF_8)
    } catch (ex: IOException) {
        ex.printStackTrace()
        null
    }
}

fun getQuestionsFromJson(context: Context, fileName: String): Map<String, List<String>> {
    val jsonFileString = loadJSONFromFile(context, fileName)
    val gson = Gson()
    val mapType = object : TypeToken<Map<String, List<String>>>() {}.type
    return gson.fromJson(jsonFileString, mapType)
}

fun calculateResults(userAnswers: Map<String, List<Int>>): Map<String, Int> {
    return userAnswers.mapValues { (_, answers) ->
        answers.sum()
    }
}
