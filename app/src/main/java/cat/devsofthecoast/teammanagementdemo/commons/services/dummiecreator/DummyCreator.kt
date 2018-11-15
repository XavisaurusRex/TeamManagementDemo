package cat.devsofthecoast.teammanagementdemo.commons.services.dummiecreator

import cat.devsofthecoast.teammanagementdemo.commons.models.Team
import cat.devsofthecoast.teammanagementdemo.commons.models.questions.*
import cat.devsofthecoast.teammanagementdemo.commons.models.users.Player
import cat.devsofthecoast.teammanagementdemo.commons.models.users.Trainer
import org.json.JSONArray
import java.util.*

object DummyCreator {

    private val NUM_TEAMS = 10
    private val PLAYERS_IN_TEAM = 12
    private val NUM_PLAYERS = NUM_TEAMS*PLAYERS_IN_TEAM
    private val NUM_TRAINERS = NUM_TEAMS
    private val DEFAULT_SURVEY_SIZE = 10
    private val NUM_QUESTIONS = NUM_TEAMS*DEFAULT_SURVEY_SIZE

    fun createQuestions(statements: JSONArray, responseOptions: JSONArray): List<Question> {
        val r = Random()
        val questions = arrayListOf<Question>()

        for (i in 0 until NUM_QUESTIONS) {
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
        val nOptions = r.nextInt(8) + 2
        for (i in 0 until nOptions) {
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

    fun createPlayers(names: JSONArray, surnames: JSONArray, emails: JSONArray): List<Player> {
        val r = Random()
        val players = arrayListOf<Player>()

        for (i in 0 until NUM_PLAYERS) {
            val player = Player()
            player.name = names.getString(r.nextInt(names.length()))
            player.surname = surnames.getString(r.nextInt(surnames.length()))
            player.email = emails.getString(r.nextInt(emails.length()))
            player.phoneNumber = r.nextInt(666667) + 695000000
            player.physical = Player.Physical(r.nextInt(200) / 100.0, r.nextInt(200) / 100.0)
            players.add(player)
        }
        return players
    }

    fun createTrainers(names: JSONArray, surnames: JSONArray, emails: JSONArray): List<Trainer> {
        val r = Random()
        val trainers = arrayListOf<Trainer>()

        for (i in 0 until NUM_TRAINERS) {
            val trainer = Trainer()
            trainer.name = names.getString(r.nextInt(names.length()))
            trainer.surname = surnames.getString(r.nextInt(surnames.length()))
            trainer.email = emails.getString(r.nextInt(emails.length()))
            trainer.phoneNumber = r.nextInt(666667) + 695000000
            trainers.add(trainer)
        }
        return trainers
    }

    fun createTeams(teamnames: JSONArray): List<Team> {
        val r = Random()
        val teams = arrayListOf<Team>()

        for (i in 0 until NUM_TEAMS) {
            val team = Team()
            team.name = teamnames.getString(r.nextInt(teamnames.length()))
            teams.add(team)
        }
        return teams
    }

    fun asociateRandomly(questions: List<Question>, players: List<Player>, trainers: List<Trainer>, teams: List<Team>) {
        for(i in 0 until teams.size){
            val team = teams[i]
            for (player in players.subList(i * PLAYERS_IN_TEAM, i * PLAYERS_IN_TEAM + PLAYERS_IN_TEAM)){
                player.team = team.key!!
                team.players.add(player.key!!)
            }

            val trainer = trainers[i]
            team.trainers.add(trainer.key!!)
            trainer.team = team.key!!

            for (question in questions.subList(i * DEFAULT_SURVEY_SIZE, i * DEFAULT_SURVEY_SIZE + DEFAULT_SURVEY_SIZE)){
                team.survey.add(question.key!!)
            }
        }
    }
}
