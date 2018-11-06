package cat.devsofthecoast.teammanagementdemo.commons.useCase.trainers

import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.useCase.Callback
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.useCase.UseCase
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.useCase.UseCaseExecutor
import cat.devsofthecoast.teammanagementdemo.commons.models.users.Trainer
import cat.devsofthecoast.teammanagementdemo.commons.repository.trainers.TrainersRepository
import cat.devsofthecoast.teammanagementdemo.commons.services.ServiceCallback

class GetTrainerUseCase(val appConfig: BaseConfig,
                        private val repository: TrainersRepository) : UseCase<String, Trainer>(appConfig) {
    override fun run(input: String?, callback: Callback<Trainer>?) {
        try {
            repository.getTrainer(input!!, object : ServiceCallback<Trainer?> {
                override fun onSuccess(value: Trainer?) {
                    callback?.onSuccess(value!!)
                }

                override fun <E : Throwable> onError(error: E) {
                    callback?.onError(error)
                }
            })
        } catch (ex: Throwable) {
            callback?.onError(ex)
        }
    }

    class Executor(config: BaseConfig, builder: (UseCaseExecutor<String, Trainer>.() -> Unit)?) :
            UseCaseExecutor<String, Trainer>(config, builder)
}