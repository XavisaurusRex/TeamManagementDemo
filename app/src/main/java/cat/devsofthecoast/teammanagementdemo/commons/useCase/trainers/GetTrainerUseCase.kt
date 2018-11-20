package cat.devsofthecoast.teammanagementdemo.commons.useCase.trainers

import android.util.Log
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.useCase.Callback
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.useCase.UseCase
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.useCase.UseCaseExecutor
import cat.devsofthecoast.teammanagementdemo.commons.models.users.Trainer
import cat.devsofthecoast.teammanagementdemo.commons.repository.trainers.TrainersRepository
import cat.devsofthecoast.teammanagementdemo.commons.services.ServiceCallback

class GetTrainerUseCase(val appConfig: BaseConfig,
                        private val repository: TrainersRepository) : UseCase<String, Trainer>(appConfig) {
    private val LOG_TAG = "GetTrainerUseCase"
    override fun run(input: String?, callback: Callback<Trainer>?) {
        try {
            repository.getTrainer(input!!, object : ServiceCallback<Trainer?> {
                override fun onSuccess(value: Trainer?) {
                    Log.d(LOG_TAG, "Trainer Recived Successful")
                    callback?.onSuccess(value!!)
                }

                override fun <E : Throwable> onError(error: E) {
                    Log.e(LOG_TAG, "Trainer Recived Error", error)
                    callback?.onError(error)
                }
            })
        } catch (ex: Throwable) {
            Log.e(LOG_TAG, "Trainer Recived Error", ex)
            callback?.onError(ex)
        }
    }

    class Executor(config: BaseConfig, builder: (UseCaseExecutor<String, Trainer>.() -> Unit)?) :
            UseCaseExecutor<String, Trainer>(config, builder)
}