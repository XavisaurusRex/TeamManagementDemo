package cat.devsofthecoast.teammanagementdemo.commons.useCase.teams

import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.useCase.Callback
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.useCase.UseCase
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.useCase.UseCaseExecutor
import cat.devsofthecoast.teammanagementdemo.commons.models.Team
import cat.devsofthecoast.teammanagementdemo.commons.repository.teams.TeamsRepository
import cat.devsofthecoast.teammanagementdemo.commons.services.ServiceCallback

class GetTeamUseCase(val appConfig: BaseConfig,
                     private val repository: TeamsRepository) : UseCase<String, Team>(appConfig) {
    override fun run(input: String?, callback: Callback<Team>?) {
        try {
            repository.getTeam(input!!, object : ServiceCallback<Team> {
                override fun onSuccess(value: Team) {
                    callback?.onSuccess(value)
                }

                override fun <E : Throwable> onError(error: E) {
                    callback?.onError(error)
                }
            })
        } catch (ex: Throwable) {
            callback?.onError(ex)
        }
    }

    class Executor(config: BaseConfig, builder: (UseCaseExecutor<String, Team>.() -> Unit)?) :
            UseCaseExecutor<String, Team>(config, builder)
}