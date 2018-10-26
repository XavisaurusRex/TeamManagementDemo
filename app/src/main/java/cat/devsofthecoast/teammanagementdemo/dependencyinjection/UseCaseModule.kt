package cat.devsofthecoast.teammanagementdemo.dependencyinjection

import cat.devsofthecoast.teammanagementdemo.commons.useCase.FillDatabaseUseCase
import cat.devsofthecoast.teammanagementdemo.commons.useCase.GetAllQuestionsUseCase
import cat.devsofthecoast.teammanagementdemo.commons.useCase.GetQuestionUseCase
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.config.BaseConfig

class UseCaseModule(
        private val appConfig: BaseConfig,
        private val repositoryModule: RepositoryModule) {

    val fillDatabaseUseCase: FillDatabaseUseCase by lazy {
        FillDatabaseUseCase(appConfig, repositoryModule.questionsRepository)
    }

    val getAllQuestionsUseCase: GetAllQuestionsUseCase by lazy {
        GetAllQuestionsUseCase(appConfig, repositoryModule.questionsRepository)
    }

    val getQuestionUseCase: GetQuestionUseCase by lazy {
        GetQuestionUseCase(appConfig, repositoryModule.questionsRepository)
    }
}
