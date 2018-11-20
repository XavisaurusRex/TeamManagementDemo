package cat.devsofthecoast.teammanagementdemo.commons.useCase.trainers

import android.util.Log
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.useCase.Callback
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.useCase.UseCase
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.useCase.UseCaseExecutor
import cat.devsofthecoast.teammanagementdemo.commons.models.users.Trainer
import cat.devsofthecoast.teammanagementdemo.commons.repository.trainers.TrainersRepository
import cat.devsofthecoast.teammanagementdemo.commons.services.ServiceCallback

class UpdateTrainerUseCase(val appConfig: BaseConfig,
                           private val repository: TrainersRepository) : UseCase<Trainer, Void?>(appConfig) {
    private val LOG_TAG = "UpdateTrainerUseCase"

    override fun run(input: Trainer?, callback: Callback<Void?>?) {
        try {
            repository.updateTrainer(input!!, object : ServiceCallback<Void?> {
                override fun onSuccess(value: Void?) {
                    Log.d(LOG_TAG, "Trainer Updated Successful")
                    callback?.onSuccess(null)
                }

                override fun <E : Throwable> onError(error: E) {
                    Log.e(LOG_TAG, "Trainer Updated Error", error)
                    callback?.onError(error)
                }
            })
        } catch (ex: Throwable) {
            Log.e(LOG_TAG, "Trainer Updated Error", ex)
            callback?.onError(ex)
        }
    }

    class Executor(config: BaseConfig, builder: (UseCaseExecutor<Trainer, Void?>.() -> Unit)?) :
            UseCaseExecutor<Trainer, Void?>(config, builder)
}