package cat.devsofthecoast.teammanagementdemo.dependencyinjection

import cat.devsofthecoast.teammanagementdemo.commons.useCase.FillDatabaseUseCase
import cat.devsofthecoast.teammanagementdemo.commons.useCase.GetAllQuestionsUseCase
import cat.devsofthecoast.teammanagementdemo.commons.useCase.GetQuestionUseCase
import cat.devsofthecoast.teammanagementdemo.core.mvp.config.BaseConfig

class UseCaseModule(
        val appConfig: BaseConfig,
        val repositoryModule: RepositoryModule) {

    val fillDatabaseUseCase: FillDatabaseUseCase by lazy {
        FillDatabaseUseCase(appConfig, repositoryModule.tmdRepository)
    }

    val getAllQuestionsUseCase: GetAllQuestionsUseCase by lazy {
        GetAllQuestionsUseCase(appConfig, repositoryModule.tmdRepository)
    }

    val getQuestionUseCase: GetQuestionUseCase by lazy {
        GetQuestionUseCase(appConfig, repositoryModule.tmdRepository)
    }
}
