package cat.devsofthecoast.teammanagementdemo.commons.services.dummiecreator

import cat.devsofthecoast.teammanagementdemo.commons.models.questions.*
import cat.devsofthecoast.teammanagementdemo.commons.models.users.Player
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

object DummyCreator {
    fun createQuestions(statements: JSONArray, responseOptions: JSONArray): List<Question> {
        val r = Random()
        val questions = arrayListOf<Question>()

        for (i in 0 until 50) {
            questions.add(getRandQuestion(r, statements, responseOptions))
        }

        return questions
    }

    private fun getRandQuestion(r: Random, statements: JSONArray, responseOptions: JSONArray): Question {
         val question: Question = when (getRandQuestionType(r)) {
            QuestionType.TYPE_BOOLEAN -> {
                val question = BooleanQuestion()
                question
            }
            QuestionType.TYPE_PLAINTEXT -> {
                val question = PlaintextQuestion()
                question
            }
            QuestionType.TYPE_SINGLECHOICE -> {
                val question = SingleChoiceQuestion()
                question.options = getRandomResponseOptions(r, responseOptions)
                question
            }
            QuestionType.TYPE_MULTICHOICE -> {
                val question = MultipleChoiceQuestion()
                question.options = getRandomResponseOptions(r, responseOptions)
                question
            }
            QuestionType.TYPE_NUMERIC -> {
                val question = NumericQuestion()
                question.max = r.nextInt(50)
                question.min = r.nextInt(question.max)
                question
            }
            else -> TODO("HumanBody Question Type Not Implemented")
        }
        question.statement = statements.getString(r.nextInt(statements.length()))
        return question
    }

    private fun getRandomResponseOptions(r: Random, responseOptions: JSONArray): List<String> {
        val list = arrayListOf<String>()
        val nOptions = r.nextInt(8)+2
        for (i in 0 until nOptions){
            list.add(responseOptions.getString(r.nextInt(responseOptions.length())))
        }
        return list
    }

    private fun getRandQuestionType(r: Random): QuestionType {
        //todo generate TypeHumanbody in future
        return when (r.nextInt(5)) {
            0 -> QuestionType.TYPE_BOOLEAN
            1 -> QuestionType.TYPE_PLAINTEXT
            2 -> QuestionType.TYPE_SINGLECHOICE
            3 -> QuestionType.TYPE_MULTICHOICE
            4 -> QuestionType.TYPE_NUMERIC
            else -> QuestionType.TYPE_HUMANBODY
        }
    }

    fun createPlayers(): List<Player> {
        val r = Random()
        val players = arrayListOf<Player>()

        for (i in 0 until 100) {
            val player = Player()
            player.name = ""
            player.surname
            player.picture_url
            player.email
            player.phoneNumber
            player.team
            player.physical
        }
        return listOf()
    }
}
