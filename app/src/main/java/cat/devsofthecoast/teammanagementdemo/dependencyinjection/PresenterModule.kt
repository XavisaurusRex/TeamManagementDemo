package cat.devsofthecoast.teammanagementdemo.dependencyinjection

import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.feature.activities.headactivity.HeadContract
import cat.devsofthecoast.teammanagementdemo.feature.activities.headactivity.presenter.HeadPresenter
import cat.devsofthecoast.teammanagementdemo.feature.activities.login.LoginContract
import cat.devsofthecoast.teammanagementdemo.feature.activities.login.presenter.LoginPresenter
import cat.devsofthecoast.teammanagementdemo.feature.activities.selectteam.presenter.SelectTeamPresenter
import cat.devsofthecoast.teammanagementdemo.feature.activities.signuptrainer.SignupContract
import cat.devsofthecoast.teammanagementdemo.feature.activities.signuptrainer.presenter.SignupPresenter
import cat.devsofthecoast.teammanagementdemo.feature.fragments.devoptions.DevOptionsContract
import cat.devsofthecoast.teammanagementdemo.feature.fragments.devoptions.presenter.DevOptionsPresenter
import cat.devsofthecoast.teammanagementdemo.feature.fragments.surveyfragment.SurveyContract
import cat.devsofthecoast.teammanagementdemo.feature.fragments.surveyfragment.presenter.SurveyPresenter
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

    val headPresenter: HeadContract.Presenter by lazy {
        HeadPresenter(
                appConfig,
                useCaseModule.getTeamUseCase
        )
    }

    val loginPresenter: LoginContract.Presenter by lazy {
        LoginPresenter(
                appConfig,
                useCaseModule.getTrainerUseCase
        )
    }

    val weekpreviewPresenter: WeekPreviewContract.Presenter by lazy {
        WeekPreviewPresenter(
                appConfig
        )
    }

    val signupPresenter: SignupContract.Presenter by lazy {
        SignupPresenter(
                appConfig,
                useCaseModule.setTrainerUseCase
        )
    }

    val selectTeamPresenter: SelectTeamPresenter by lazy {
        SelectTeamPresenter(
                appConfig,
                useCaseModule.getTeamsUseCase,
                useCaseModule.linkTeamTrainerUseCase
        )
    }
}
