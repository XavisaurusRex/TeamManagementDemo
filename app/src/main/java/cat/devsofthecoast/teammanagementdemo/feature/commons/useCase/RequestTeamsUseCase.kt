package cat.devsofthecoast.teammanagementdemo.feature.commons.useCase

import cat.devsofthecoast.teammanagementdemo.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.core.mvp.useCase.UseCase
import cat.devsofthecoast.teammanagementdemo.core.mvp.useCase.UseCaseExecutor
import cat.devsofthecoast.teammanagementdemo.feature.commons.exceptions.InvalidTeamsException
import cat.devsofthecoast.teammanagementdemo.feature.commons.repository.TMDRepository
import cat.devsofthecoast.teammanagementdemo.feature.commons.repository.impl.Team

class RequestTeamsUseCase(val appConfig: BaseConfig,
                          private val repository: TMDRepository) : UseCase<Void, List<Team>>(appConfig) {
    override fun run(input: Void?, callback: Callback<List<Team>>?) {
        val result = repository.getTeams()
        if (result != null) {
            callback?.onSuccess(result)
        } else {
            callback?.onError(InvalidTeamsException())
        }
    }

    class Executor(config: BaseConfig, builder: (UseCaseExecutor<Void, List<Team>>.() -> Unit)?) :
            UseCaseExecutor<Void, List<Team>>(config, builder)
}