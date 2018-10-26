package cat.devsofthecoast.teammanagementdemo.dependencyinjection

import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.feature.devoptions.DevOptionsContract
import cat.devsofthecoast.teammanagementdemo.feature.devoptions.presenter.DevOptionsPresenter
import cat.devsofthecoast.teammanagementdemo.feature.weekoverview.WeekOverviewContract
import cat.devsofthecoast.teammanagementdemo.feature.weekoverview.presenter.WeekOverviewPresenter

class PresenterModule(
        private val appConfig: BaseConfig,
        private val useCaseModule: UseCaseModule) {

    val devOptionsPresenter: DevOptionsContract.Presenter by lazy {
        DevOptionsPresenter(
                appConfig,
                useCaseModule.fillDatabaseUseCase,
                useCaseModule.getAllQuestionsUseCase,
                useCaseModule.getQuestionUseCase)
    }

    val weekOverviewPresenter: WeekOverviewContract.Presenter by lazy {
        WeekOverviewPresenter(
                appConfig,
                useCaseModule.getQuestionUseCase)
    }
}
