package cat.devsofthecoast.teammanagementdemo.feature.activities.selectteam.presenter

import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.commons.useCase.teams.GetTeamsUseCase
import cat.devsofthecoast.teammanagementdemo.feature.activities.selectteam.SelectTeamContract

class SelectTeamPresenter(
        private val appConfig: BaseConfig,
        private val getTeamsUseCase: GetTeamsUseCase)
    : SelectTeamContract.Presenter() {

    override fun getTeams() {
        GetTeamsUseCase.Executor(appConfig) {
            useCase = getTeamsUseCase
            onSuccess = {
                view?.onGetTeamsSuccess(it)
            }
            onError = {
                view?.onGetTeamsError(it)
            }
        }.execute(null)
    }
}