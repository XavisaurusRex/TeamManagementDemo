package cat.devsofthecoast.teammanagementdemo.commons.services.dummiecreator

import cat.devsofthecoast.teammanagementdemo.commons.models.DailyEntry
import cat.devsofthecoast.teammanagementdemo.commons.models.Team
import cat.devsofthecoast.teammanagementdemo.commons.models.questions.*
import cat.devsofthecoast.teammanagementdemo.commons.models.users.Player
import cat.devsofthecoast.teammanagementdemo.commons.models.users.Trainer
import org.json.JSONArray
import java.util.*
import kotlin.collections.ArrayList

object DummyCreator {

    private val NUM_TEAMS = 10
    private val PLAYERS_IN_TEAM = 12
    private val NUM_PLAYERS = NUM_TEAMS * PLAYERS_IN_TEAM
    private val NUM_TRAINERS = NUM_TEAMS
    private val DEFAULT_SURVEY_SIZE = 10
    private val NUM_QUESTIONS = NUM_TEAMS * DEFAULT_SURVEY_SIZE

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

    fun createDailyEntries(teams: List<Team>, questions: List<Question>, randomResponses: JSONArray): ArrayList<DailyEntry> {
        val random = Random()

        val dailyEntries = arrayListOf<DailyEntry>()
        for (day in 0 until 20) {
            val dailyEntry = DailyEntry()
            for (team in teams) {
                val teamsResponse = DailyEntry.TeamResponses()
                teamsResponse.team = team.key
                teamsResponse.survey = team.survey

                for (player in team.players) {
                    val playerResponses = hashMapOf<String, Any>()
                    for (question_id in team.survey) {
                        val act_quest = getQuest(questions, question_id)
                        when (act_quest?.type) {
                            QuestionType.TYPE_BOOLEAN -> playerResponses[question_id] = random.nextBoolean()
                            QuestionType.TYPE_PLAINTEXT -> playerResponses[question_id] = randomResponses.getString(random.nextInt(randomResponses.length()))
                            QuestionType.TYPE_SINGLECHOICE -> playerResponses[question_id] = random.nextInt((act_quest as SingleChoiceQuestion).options.size)
                            QuestionType.TYPE_MULTICHOICE -> playerResponses[question_id] = listOf(random.nextInt((act_quest as SingleChoiceQuestion).options.size))
                            QuestionType.TYPE_NUMERIC -> {
                                val questt = act_quest as NumericQuestion
                                playerResponses[question_id] = random.nextInt(questt.max - questt.min) + questt.min
                            }
                            QuestionType.TYPE_HUMANBODY -> TODO()
                            null -> TODO()
                        }
                    }
                    teamsResponse.playersResponses[player] = playerResponses
                }

                dailyEntry.teamResponses[team.key!!] = teamsResponse
            }

            dailyEntries.add(dailyEntry)
        }
        return dailyEntries
    }

    private fun getQuest(questions: List<Question>, question_id: String): Question? {
        var question: Question? = null
        var iteration: Int = -1
        var found = false
        while (iteration < questions.size && !found) {
            found = questions[iteration].key == question_id
            iteration++
        }
        if (found) {
            question = questions[iteration-1]
        }
        return question
    }

    fun asociateRandomly(questions: List<Question>, players: List<Player>, trainers: List<Trainer>, teams: List<Team>) {
        for (i in 0 until teams.size) {
            val team = teams[i]
            for (player in players.subList(i * PLAYERS_IN_TEAM, i * PLAYERS_IN_TEAM + PLAYERS_IN_TEAM)) {
                player.team = team.key!!
                team.players.add(player.key!!)
            }

            val trainer = trainers[i]
            team.trainer = trainer.key!!
            trainer.team = team.key!!

            for (question in questions.subList(i * DEFAULT_SURVEY_SIZE, i * DEFAULT_SURVEY_SIZE + DEFAULT_SURVEY_SIZE)) {
                team.survey.add(question.key!!)
            }
        }
    }
}
