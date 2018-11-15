package cat.devsofthecoast.teammanagementdemo.dependencyinjection

import android.content.Context
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.commons.useCase.associate.LinkTeamTrainerUseCase
import cat.devsofthecoast.teammanagementdemo.commons.useCase.demo.ClearDatabseChildUseCase
import cat.devsofthecoast.teammanagementdemo.commons.useCase.demo.FillDatabaseUseCase
import cat.devsofthecoast.teammanagementdemo.commons.useCase.questions.GetAllQuestionsUseCase
import cat.devsofthecoast.teammanagementdemo.commons.useCase.questions.GetQuestionUseCase
import cat.devsofthecoast.teammanagementdemo.commons.useCase.teams.GetTeamUseCase
import cat.devsofthecoast.teammanagementdemo.commons.useCase.teams.GetTeamsUseCase
import cat.devsofthecoast.teammanagementdemo.commons.useCase.teams.SetTeamUseCase
import cat.devsofthecoast.teammanagementdemo.commons.useCase.trainers.GetTrainerUseCase
import cat.devsofthecoast.teammanagementdemo.commons.useCase.trainers.SetTrainerUseCase

class UseCaseModule(
        private val appConfig: BaseConfig,
        private val context: Context,
        private val repositoryModule: RepositoryModule) {

    val fillDatabaseUseCase: FillDatabaseUseCase by lazy {
        FillDatabaseUseCase(appConfig, context,
                repositoryModule.questionsRepository,
                repositoryModule.playersRepository,
                repositoryModule.teamsRepository,
                repositoryModule.trainersRepository)
    }

    val getAllQuestionsUseCase: GetAllQuestionsUseCase by lazy {
        GetAllQuestionsUseCase(appConfig, repositoryModule.questionsRepository)
    }

    val getQuestionUseCase: GetQuestionUseCase by lazy {
        GetQuestionUseCase(appConfig, repositoryModule.questionsRepository)
    }

    val clearDatabseChildUseCase: ClearDatabseChildUseCase by lazy {
        ClearDatabseChildUseCase(appConfig, repositoryModule.questionsRepository)
    }

    val getTrainerUseCase: GetTrainerUseCase by lazy {
        GetTrainerUseCase(appConfig, repositoryModule.trainersRepository)
    }

    val setTrainerUseCase: SetTrainerUseCase by lazy {
        SetTrainerUseCase(appConfig, repositoryModule.trainersRepository)
    }

    val getTeamsUseCase: GetTeamsUseCase by lazy {
        GetTeamsUseCase(appConfig, repositoryModule.teamsRepository)
    }

    val setTeamUseCase: SetTeamUseCase by lazy {
        SetTeamUseCase(
                appConfig,
                repositoryModule.teamsRepository)
    }

    val getTeamUseCase: GetTeamUseCase by lazy {
        GetTeamUseCase(
                appConfig,
                repositoryModule.teamsRepository
        )
    }

    val linkTeamTrainerUseCase: LinkTeamTrainerUseCase by lazy {
        LinkTeamTrainerUseCase(
                appConfig,
                repositoryModule.teamsRepository,
                repositoryModule.trainersRepository
        )
    }

}
