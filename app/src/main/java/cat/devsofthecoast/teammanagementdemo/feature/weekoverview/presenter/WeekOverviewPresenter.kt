package cat.devsofthecoast.teammanagementdemo.feature.weekoverview.presenter

import cat.devsofthecoast.teammanagementdemo.commons.useCase.GetQuestionUseCase
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.feature.weekoverview.WeekOverviewContract

class WeekOverviewPresenter(
        private val appConfig: BaseConfig,
        private val getQuestionUseCase: GetQuestionUseCase)
    : WeekOverviewContract.Presenter() {

    override fun getQuestion(key: String) {
        GetQuestionUseCase.Executor(appConfig, builder = {
            useCase = getQuestionUseCase
            onSuccess = {
                view?.onGetQuestionSuccess(it)
            }
            onError = {
                view?.onGetQuestionError(it)
            }
        }).execute(key)
    }

}