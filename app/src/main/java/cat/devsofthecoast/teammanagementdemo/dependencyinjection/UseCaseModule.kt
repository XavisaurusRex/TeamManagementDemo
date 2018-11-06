package cat.devsofthecoast.teammanagementdemo.dependencyinjection

import android.content.Context
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.commons.useCase.ClearDatabseChildUseCase
import cat.devsofthecoast.teammanagementdemo.commons.useCase.demo.FillDatabaseUseCase
import cat.devsofthecoast.teammanagementdemo.commons.useCase.questions.GetAllQuestionsUseCase
import cat.devsofthecoast.teammanagementdemo.commons.useCase.questions.GetQuestionUseCase
import cat.devsofthecoast.teammanagementdemo.commons.useCase.trainers.GetTrainerUseCase

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

}
