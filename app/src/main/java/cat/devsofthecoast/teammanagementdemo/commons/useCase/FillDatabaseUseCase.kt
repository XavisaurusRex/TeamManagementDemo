package cat.devsofthecoast.teammanagementdemo.commons.useCase

import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.useCase.Callback
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.useCase.UseCase
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.useCase.UseCaseExecutor
import cat.devsofthecoast.teammanagementdemo.commons.repository.questions.QuestionsRepository
import cat.devsofthecoast.teammanagementdemo.commons.services.ServiceCallback

class FillDatabaseUseCase(val appConfig: BaseConfig,
                          private val repository: QuestionsRepository) : UseCase<Void?, Boolean>(appConfig) {
    override fun run(input: Void?, callback: Callback<Boolean>?) {
        try {
            repository.setDummieDatabase(object : ServiceCallback<Boolean> {
                override fun onSuccess(value: Boolean) {
                    callback?.onSuccess(value)
                }

                override fun <E : Throwable> onError(error: E) {
                    callback?.onError(error)
                }
            })
        } catch (ex: Exception) {
            callback?.onError(ex)
        }
    }

    class Executor(config: BaseConfig, builder: (UseCaseExecutor<Void?, Boolean>.() -> Unit)?) :
            UseCaseExecutor<Void?, Boolean>(config, builder)
}