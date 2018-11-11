package cat.devsofthecoast.teammanagementdemo.commons.useCase.demo

import android.content.Context
import cat.devsofthecoast.teammanagementdemo.R
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.useCase.Callback
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.useCase.UseCase
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.useCase.UseCaseExecutor
import cat.devsofthecoast.teammanagementdemo.commons.models.DailyEntry
import cat.devsofthecoast.teammanagementdemo.commons.models.Team
import cat.devsofthecoast.teammanagementdemo.commons.models.questions.Question
import cat.devsofthecoast.teammanagementdemo.commons.models.users.Player
import cat.devsofthecoast.teammanagementdemo.commons.models.users.Trainer
import cat.devsofthecoast.teammanagementdemo.commons.repository.dailyentries.DailyEntriesRepository
import cat.devsofthecoast.teammanagementdemo.commons.repository.players.PlayersRepository
import cat.devsofthecoast.teammanagementdemo.commons.repository.questions.QuestionsRepository
import cat.devsofthecoast.teammanagementdemo.commons.repository.teams.TeamsRepository
import cat.devsofthecoast.teammanagementdemo.commons.repository.trainers.TrainersRepository
import cat.devsofthecoast.teammanagementdemo.commons.services.ServiceCallback
import cat.devsofthecoast.teammanagementdemo.commons.services.dummiecreator.DummyCreator
import cat.devsofthecoast.teammanagementdemo.commons.utilities.openRawResource
import org.json.JSONObject
import java.util.*

class FillDatabaseUseCase(
        private val appConfig: BaseConfig,
        private val context: Context,
        private val questionRepository: QuestionsRepository,
        private val playersRepository: PlayersRepository,
        private val teamsRepository: TeamsRepository,
        private val trainersRepository: TrainersRepository,
        private val dailyEntriesRepository: DailyEntriesRepository)
    : UseCase<Void?, Boolean>(appConfig) {

    override fun run(input: Void?, callback: Callback<Boolean>?) {
        try {
            val jsonObject = JSONObject(context.openRawResource(R.raw.randominfo))

            val questions: List<Question> = DummyCreator.createQuestions(jsonObject.getJSONArray("question_statments"), jsonObject.getJSONArray("questionoptions"))
            val players: List<Player> = DummyCreator.createPlayers(jsonObject.getJSONArray("names"), jsonObject.getJSONArray("surnames"), jsonObject.getJSONArray("emails"))
            val trainers: List<Trainer> = DummyCreator.createTrainers(jsonObject.getJSONArray("names"), jsonObject.getJSONArray("surnames"), jsonObject.getJSONArray("emails"))
            val teams: List<Team> = DummyCreator.createTeams(jsonObject.getJSONArray("teamnames"))

            assignKeys(questions, players, trainers, teams)

            val dailyEntries: List<DailyEntry> = DummyCreator.createDailyEntries(teams, questions, jsonObject.getJSONArray("question_statments"))
            assignKeys(dailyEntries)

            DummyCreator.asociateRandomly(questions, players, trainers, teams)

            setQuestions(questions) {
                setPlayers(players) {
                    setTeams(teams) {
                        setTrainers(trainers) {
                            setDailyEntries(dailyEntries) {
                                callback?.onSuccess(true)
                            }
                        }
                    }
                }
            }
        } catch (ex: Throwable) {
            ex.printStackTrace()
            callback?.onError(ex)
        }
    }

    private fun assignKeys(questions: List<Question>, players: List<Player>, trainers: List<Trainer>, teams: List<Team>) {
        questionRepository.assignKeys(questions)
        playersRepository.assignKeys(players)
        teamsRepository.assignKeys(teams)
        trainersRepository.assignKeys(trainers)
    }

    private fun assignKeys(dailyEntries: List<DailyEntry>) {
        val calendar = Calendar.getInstance()
        val formatedCalendar = Calendar.getInstance()

        formatedCalendar.timeInMillis = 0
        formatedCalendar.set(
                calendar[Calendar.YEAR],
                calendar[Calendar.MONTH],
                calendar[Calendar.DATE]
        )

        for (dailyEntry in dailyEntries){
            dailyEntry.key = formatedCalendar.timeInMillis.toString()
            formatedCalendar.set(
                    calendar[Calendar.YEAR],
                    calendar[Calendar.MONTH],
                    calendar[Calendar.DATE]-1
            )
        }
    }

    private fun setPlayers(players: List<Player>, function: () -> Unit) {
        playersRepository.setPlayers(players, object : ServiceCallback<Boolean> {
            override fun onSuccess(value: Boolean) {
                function.invoke()
            }

            override fun <E : Throwable> onError(error: E) {
                error.printStackTrace()
            }
        })
    }

    private fun setQuestions(questions: List<Question>, function: () -> Unit) {
        questionRepository.setQuestions(questions, object : ServiceCallback<Boolean> {
            override fun onSuccess(value: Boolean) {
                function.invoke()
            }

            override fun <E : Throwable> onError(error: E) {
                throw error
            }
        })
    }

    private fun setTeams(teams: List<Team>, function: () -> Unit) {
        teamsRepository.setTeams(teams, object : ServiceCallback<Boolean> {
            override fun onSuccess(value: Boolean) {
                function.invoke()
            }

            override fun <E : Throwable> onError(error: E) {
                error.printStackTrace()
            }
        })
    }

    private fun setTrainers(trainers: List<Trainer>, function: () -> Unit) {
        trainersRepository.setTrainers(trainers, object : ServiceCallback<Boolean> {
            override fun onSuccess(value: Boolean) {
                function.invoke()
            }

            override fun <E : Throwable> onError(error: E) {
                throw error
            }
        })
    }

    private fun setDailyEntries(dailyEntries: List<DailyEntry>, function: () -> Unit?) {
        dailyEntriesRepository.setDailyEntries(dailyEntries, object : ServiceCallback<Void?> {
            override fun onSuccess(value: Void?) {
                function.invoke()
            }

            override fun <E : Throwable> onError(error: E) {
                throw error
            }
        })
    }

    class Executor(config: BaseConfig, builder: (UseCaseExecutor<Void?, Boolean>.() -> Unit)?) :
            UseCaseExecutor<Void?, Boolean>(config, builder)
}