package cat.devsofthecoast.teammanagementdemo.commons.useCase.associate

import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.useCase.Callback
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.useCase.UseCase
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.useCase.UseCaseExecutor
import cat.devsofthecoast.teammanagementdemo.commons.models.Team
import cat.devsofthecoast.teammanagementdemo.commons.models.users.Trainer
import cat.devsofthecoast.teammanagementdemo.commons.repository.teams.TeamsRepository
import cat.devsofthecoast.teammanagementdemo.commons.repository.trainers.TrainersRepository
import cat.devsofthecoast.teammanagementdemo.commons.services.ServiceCallback

class LinkTeamTrainerUseCase(val appConfig: BaseConfig,
                             private val teamsRepository: TeamsRepository,
                             private val trainersRepository: TrainersRepository) : UseCase<LinkTeamTrainerUseCase.InputParams, Void?>(appConfig) {
    override fun run(input: InputParams?, callback: Callback<Void?>?) {
        try {
            val team = input!!.team
            val trainer = input.trainer
            team.trainers.add(trainer.key!!)
            trainer.team = team.key!!

            updateTeam(team) {
                updateTrainer(trainer) {
                    callback?.onSuccess(null)
                }
            }
        } catch (ex: Throwable) {
            callback?.onError(ex)
        }
    }

    private fun updateTeam(team: Team, function: () -> Unit) {
        teamsRepository.setTeam(team, object : ServiceCallback<Void?> {
            override fun onSuccess(value: Void?) {
                function.invoke()
            }

            override fun <E : Throwable> onError(error: E) {
                error.printStackTrace()
            }
        })
    }

    private fun updateTrainer(trainer: Trainer, function: () -> Unit) {
        trainersRepository.setTrainer(trainer, object : ServiceCallback<Void?> {
            override fun onSuccess(value: Void?) {
                function.invoke()
            }

            override fun <E : Throwable> onError(error: E) {
                error.printStackTrace()
            }
        })
    }

    data class InputParams(val team: Team, val trainer: Trainer)

    class Executor(config: BaseConfig, builder: (UseCaseExecutor<InputParams, Void?>.() -> Unit)?) :
            UseCaseExecutor<InputParams, Void?>(config, builder)
}