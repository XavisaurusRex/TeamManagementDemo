package cat.devsofthecoast.teammanagementdemo.dependencyinjection

import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.feature.devoptions.DevOptionsContract
import cat.devsofthecoast.teammanagementdemo.feature.devoptions.presenter.DevOptionsPresenter
import cat.devsofthecoast.teammanagementdemo.feature.surveyfragment.SurveyContract
import cat.devsofthecoast.teammanagementdemo.feature.surveyfragment.presenter.SurveyPresenter
import cat.devsofthecoast.teammanagementdemo.feature.headactivity.HeadContract
import cat.devsofthecoast.teammanagementdemo.feature.headactivity.presenter.HeadPresenter

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

    val weekpreviewPresenter: HeadContract.Presenter by lazy {
        HeadPresenter(
                appConfig
        )
    }
}
