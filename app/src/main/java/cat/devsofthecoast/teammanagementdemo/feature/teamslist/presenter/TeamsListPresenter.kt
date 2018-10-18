package cat.devsofthecoast.teammanagementdemo.feature.teamslist.presenter

import cat.devsofthecoast.teammanagementdemo.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.feature.commons.useCase.RequestTeamsUseCase
import cat.devsofthecoast.teammanagementdemo.feature.teamslist.TeamsListContract

class TeamsListPresenter(
        private val appConfig: BaseConfig,
        private val requestTeamsUseCase: RequestTeamsUseCase)
    : TeamsListContract.Presenter() {

    override fun getTeams() {
        RequestTeamsUseCase.Executor(appConfig) {
            useCase = requestTeamsUseCase
            onSuccess = {
                view?.showTeams(it)
            }
        }.execute(null)
    }
}