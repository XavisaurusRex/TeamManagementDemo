package cat.devsofthecoast.teammanagementdemo.commons.useCase.teams

import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.useCase.Callback
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.useCase.UseCase
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.useCase.UseCaseExecutor
import cat.devsofthecoast.teammanagementdemo.commons.models.Team
import cat.devsofthecoast.teammanagementdemo.commons.repository.teams.TeamsRepository
import cat.devsofthecoast.teammanagementdemo.commons.services.ServiceCallback

class GetTeamsUseCase(val appConfig: BaseConfig,
                      private val repository: TeamsRepository) : UseCase<Void?, ArrayList<Team>>(appConfig) {
    override fun run(input: Void?, callback: Callback<ArrayList<Team>>?) {
        try {
            repository.getTeams(object : ServiceCallback<ArrayList<Team>> {
                override fun onSuccess(value: ArrayList<Team>) {
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

    class Executor(config: BaseConfig, builder: (UseCaseExecutor<Void?, ArrayList<Team>>.() -> Unit)?) :
            UseCaseExecutor<Void?, ArrayList<Team>>(config, builder)
}