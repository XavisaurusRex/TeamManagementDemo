package cat.devsofthecoast.teammanagementdemo.feature.devoptions.usecase

import android.content.Context
import cat.devsofthecoast.teammanagementdemo.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.core.mvp.useCase.UseCase
import cat.devsofthecoast.teammanagementdemo.core.mvp.useCase.UseCaseExecutor
import cat.devsofthecoast.teammanagementdemo.feature.commons.exceptions.InvalidTeamsException
import cat.devsofthecoast.teammanagementdemo.feature.commons.repository.TMDRepository
import cat.devsofthecoast.teammanagementdemo.feature.commons.repository.impl.Team
import cat.devsofthecoast.teammanagementdemo.feature.commons.utilities.readJsonFile

class FillDatabaseUseCase(val appConfig: BaseConfig,
                          private val repository: TMDRepository,
                          private val context: Context) : UseCase<String, Boolean>(appConfig) {
    override fun run(input: String?, callback: Callback<Boolean>?) {

        val json: String? = context.readJsonFile(input!!)
        val result = repository.fillDatabase(input)
        if (result) {
            callback?.onSuccess(result)
        } else {
            callback?.onError(InvalidTeamsException())
        }
    }

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
}