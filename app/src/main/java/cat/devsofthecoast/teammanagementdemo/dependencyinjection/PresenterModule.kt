package cat.devsofthecoast.teammanagementdemo.dependencyinjection

import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.feature.devoptions.DevOptionsContract
import cat.devsofthecoast.teammanagementdemo.feature.devoptions.presenter.DevOptionsPresenter
import cat.devsofthecoast.teammanagementdemo.feature.weekoverview.SurveyContract
import cat.devsofthecoast.teammanagementdemo.feature.weekoverview.presenter.SurveyPresenter

class PresenterModule(
        private val appConfig: BaseConfig,
        private val useCaseModule: UseCaseModule) {

    val devOptionsPresenter: DevOptionsContract.Presenter by lazy {
        DevOptionsPresenter(
                appConfig,
                useCaseModule.fillDatabaseUseCase,
                useCaseModule.getAllQuestionsUseCase,
                useCaseModule.getQuestionUseCase,
                useCaseModule.clearDatabseChildUseCase)
    }

    val surveyPresenter: SurveyContract.Presenter by lazy {
        SurveyPresenter(
                appConfig,
                useCaseModule.getQuestionUseCase,
                useCaseModule.getAllQuestionsUseCase)
    }
}
