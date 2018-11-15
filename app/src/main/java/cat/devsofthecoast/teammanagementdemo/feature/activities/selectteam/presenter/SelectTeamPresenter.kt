package cat.devsofthecoast.teammanagementdemo.feature.activities.selectteam.presenter

import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.commons.models.Team
import cat.devsofthecoast.teammanagementdemo.commons.models.users.Trainer
import cat.devsofthecoast.teammanagementdemo.commons.useCase.associate.LinkTeamTrainerUseCase
import cat.devsofthecoast.teammanagementdemo.commons.useCase.teams.GetTeamsUseCase
import cat.devsofthecoast.teammanagementdemo.feature.activities.selectteam.SelectTeamContract

class SelectTeamPresenter(
        private val appConfig: BaseConfig,
        private val getTeamsUseCase: GetTeamsUseCase,
        private val linkTeamTrainerUseCase: LinkTeamTrainerUseCase)
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

    override fun associateTeamToTrainer(team: Team, loggedTrainer: Trainer) {
        LinkTeamTrainerUseCase.Executor(appConfig) {
            useCase = linkTeamTrainerUseCase
            onSuccess = {
                view?.onLinkTeamTrainerSuccess()
            }
            onError = {
                view?.onLinkTeamTrainerError(it)
            }
        }.execute(LinkTeamTrainerUseCase.InputParams(team, loggedTrainer))
    }
}