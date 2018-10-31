package cat.devsofthecoast.teammanagementdemo.dependencyinjection

import android.content.Context
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.commons.useCase.ClearDatabseChildUseCase
import cat.devsofthecoast.teammanagementdemo.commons.useCase.FillDatabaseUseCase
import cat.devsofthecoast.teammanagementdemo.commons.useCase.GetAllQuestionsUseCase
import cat.devsofthecoast.teammanagementdemo.commons.useCase.GetQuestionUseCase

class UseCaseModule(
        private val appConfig: BaseConfig,
        private val context: Context,
        private val repositoryModule: RepositoryModule) {

    val fillDatabaseUseCase: FillDatabaseUseCase by lazy {
        FillDatabaseUseCase(appConfig, context,
                repositoryModule.questionsRepository,
                repositoryModule.playersRepository)
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
}
