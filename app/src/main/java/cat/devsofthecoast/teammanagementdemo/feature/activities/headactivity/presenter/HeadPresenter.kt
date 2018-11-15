package cat.devsofthecoast.teammanagementdemo.feature.activities.headactivity.presenter

import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.commons.useCase.teams.GetTeamUseCase
import cat.devsofthecoast.teammanagementdemo.feature.activities.headactivity.HeadContract

class HeadPresenter(
        private val appConfig: BaseConfig,
        private val getTeamUseCase: GetTeamUseCase)
    : HeadContract.Presenter() {
    override fun getTeam(teamKey: String) {
        GetTeamUseCase.Executor(appConfig) {
            useCase = getTeamUseCase
            onSuccess = {
                view?.onGetTeamSuccess(it)
            }
            onError = {
                view?.onGetTeamError(it)
            }
        }.execute(teamKey)
    }
}