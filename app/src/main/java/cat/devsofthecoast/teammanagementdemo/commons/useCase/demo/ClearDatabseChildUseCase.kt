package cat.devsofthecoast.teammanagementdemo.commons.useCase.demo

import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.useCase.Callback
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.useCase.UseCase
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.useCase.UseCaseExecutor
import cat.devsofthecoast.teammanagementdemo.commons.repository.questions.QuestionsRepository
import cat.devsofthecoast.teammanagementdemo.commons.services.ServiceCallback

class ClearDatabseChildUseCase(val appConfig: BaseConfig,
                               private val repository: QuestionsRepository) : UseCase<String, Boolean>(appConfig) {
    override fun run(input: String?, callback: Callback<Boolean>?) {
        try {
            repository.clearChild(input!!, object : ServiceCallback<Boolean> {
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

    class Executor(config: BaseConfig, builder: (UseCaseExecutor<String, Boolean>.() -> Unit)?) :
            UseCaseExecutor<String, Boolean>(config, builder)
}