package cat.devsofthecoast.teammanagementdemo.dependencyinjection

import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.feature.devoptions.DevOptionsContract
import cat.devsofthecoast.teammanagementdemo.feature.devoptions.presenter.DevOptionsPresenter
import cat.devsofthecoast.teammanagementdemo.feature.surveyactivity.SurveyContract
import cat.devsofthecoast.teammanagementdemo.feature.surveyactivity.presenter.SurveyPresenter
import cat.devsofthecoast.teammanagementdemo.feature.weekpreview.WeekPreviewContract
import cat.devsofthecoast.teammanagementdemo.feature.weekpreview.presenter.WeekPreviewPresenter

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

    val weekpreviewPresenter: WeekPreviewContract.Presenter by lazy {
        WeekPreviewPresenter(
                appConfig
        )
    }
}
