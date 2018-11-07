package cat.devsofthecoast.teammanagementdemo.commons.useCase.trainers

import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.useCase.Callback
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.useCase.UseCase
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.useCase.UseCaseExecutor
import cat.devsofthecoast.teammanagementdemo.commons.models.users.Trainer
import cat.devsofthecoast.teammanagementdemo.commons.repository.trainers.TrainersRepository
import cat.devsofthecoast.teammanagementdemo.commons.services.ServiceCallback

class SetTrainerUseCase(val appConfig: BaseConfig,
                        private val repository: TrainersRepository) : UseCase<Trainer, Void?>(appConfig) {
    override fun run(input: Trainer?, callback: Callback<Void?>?) {
        try {
            repository.setTrainer(input!!, object : ServiceCallback<Void?> {
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

    class Executor(config: BaseConfig, builder: (UseCaseExecutor<Trainer, Void?>.() -> Unit)?) :
            UseCaseExecutor<Trainer, Void?>(config, builder)
}