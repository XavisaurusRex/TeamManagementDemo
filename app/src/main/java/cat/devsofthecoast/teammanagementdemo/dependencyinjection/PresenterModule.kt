package cat.devsofthecoast.teammanagementdemo.dependencyinjection

import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.feature.fragments.devoptions.DevOptionsContract
import cat.devsofthecoast.teammanagementdemo.feature.fragments.devoptions.presenter.DevOptionsPresenter
import cat.devsofthecoast.teammanagementdemo.feature.fragments.surveyfragment.SurveyContract
import cat.devsofthecoast.teammanagementdemo.feature.fragments.surveyfragment.presenter.SurveyPresenter
import cat.devsofthecoast.teammanagementdemo.feature.activities.headactivity.HeadContract
import cat.devsofthecoast.teammanagementdemo.feature.activities.headactivity.presenter.HeadPresenter
import cat.devsofthecoast.teammanagementdemo.feature.activities.login.LoginContract
import cat.devsofthecoast.teammanagementdemo.feature.activities.login.presenter.LoginPresenter
import cat.devsofthecoast.teammanagementdemo.feature.fragments.weekpreview.WeekPreviewContract
import cat.devsofthecoast.teammanagementdemo.feature.fragments.weekpreview.presenter.WeekPreviewPresenter

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

    val headContract: HeadContract.Presenter by lazy {
        HeadPresenter(
                appConfig
        )
    }

    val loginPresenter: LoginContract.Presenter by lazy {
        LoginPresenter(
                appConfig
        )
    }

    val weekpreviewPresenter: WeekPreviewContract.Presenter by lazy {
        WeekPreviewPresenter(
                appConfig
        )
    }
}
