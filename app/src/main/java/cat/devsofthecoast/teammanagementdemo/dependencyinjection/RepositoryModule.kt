package cat.devsofthecoast.teammanagementdemo.dependencyinjection

import cat.devsofthecoast.teammanagementdemo.commons.repository.dailyentries.DailyEntriesRepository
import cat.devsofthecoast.teammanagementdemo.commons.repository.dailyentries.impl.DailyEntriesRepositoryImpl
import cat.devsofthecoast.teammanagementdemo.commons.repository.players.PlayersRepository
import cat.devsofthecoast.teammanagementdemo.commons.repository.players.impl.PlayersRepositoryImpl
import cat.devsofthecoast.teammanagementdemo.commons.repository.questions.QuestionsRepository
import cat.devsofthecoast.teammanagementdemo.commons.repository.questions.impl.QuestionsRepositoryImpl
import cat.devsofthecoast.teammanagementdemo.commons.repository.teams.TeamsRepository
import cat.devsofthecoast.teammanagementdemo.commons.repository.teams.impl.TeamsRepositoryImpl
import cat.devsofthecoast.teammanagementdemo.commons.repository.trainers.TrainersRepository
import cat.devsofthecoast.teammanagementdemo.commons.repository.trainers.impl.TrainersRepositoryImpl

class RepositoryModule {
    val questionsRepository: QuestionsRepository by lazy {
        QuestionsRepositoryImpl()
    }

    val teamsRepository: TeamsRepository by lazy {
        TeamsRepositoryImpl()
    }

    val playersRepository: PlayersRepository by lazy {
        PlayersRepositoryImpl()
    }

    val trainersRepository: TrainersRepository by lazy {
        TrainersRepositoryImpl()
    }

    val dailyEntriesRepository: DailyEntriesRepository by lazy {
        DailyEntriesRepositoryImpl()
    }
}
