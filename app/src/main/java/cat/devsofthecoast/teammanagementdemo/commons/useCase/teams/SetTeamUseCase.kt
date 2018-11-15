package cat.devsofthecoast.teammanagementdemo.commons.useCase.teams

import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.useCase.Callback
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.useCase.UseCase
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.useCase.UseCaseExecutor
import cat.devsofthecoast.teammanagementdemo.commons.models.Team
import cat.devsofthecoast.teammanagementdemo.commons.models.users.Trainer
import cat.devsofthecoast.teammanagementdemo.commons.repository.teams.TeamsRepository
import cat.devsofthecoast.teammanagementdemo.commons.repository.trainers.TrainersRepository
import cat.devsofthecoast.teammanagementdemo.commons.services.ServiceCallback

class SetTeamUseCase(val appConfig: BaseConfig,
                     private val repository: TeamsRepository) : UseCase<Team, Void?>(appConfig) {
    override fun run(input: Team?, callback: Callback<Void?>?) {
        try {
            repository.setTeam(input!!, object : ServiceCallback<Void?> {
                override fun onSuccess(value: Void?) {
                    callback?.onSuccess(null)
                }

                override fun <E : Throwable> onError(error: E) {
                    callback?.onError(error)
                }
            })
        } catch (ex: Throwable) {
            callback?.onError(ex)
        }
    }

    class Executor(config: BaseConfig, builder: (UseCaseExecutor<Team, Void?>.() -> Unit)?) :
            UseCaseExecutor<Team, Void?>(config, builder)
}