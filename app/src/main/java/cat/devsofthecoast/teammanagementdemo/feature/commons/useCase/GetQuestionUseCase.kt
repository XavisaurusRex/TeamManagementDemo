package cat.devsofthecoast.teammanagementdemo.feature.commons.useCase

import cat.devsofthecoast.teammanagementdemo.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.core.mvp.useCase.Callback
import cat.devsofthecoast.teammanagementdemo.core.mvp.useCase.UseCase
import cat.devsofthecoast.teammanagementdemo.core.mvp.useCase.UseCaseExecutor
import cat.devsofthecoast.teammanagementdemo.feature.commons.exceptions.NullResponseFirebase
import cat.devsofthecoast.teammanagementdemo.feature.commons.models.questions.Question
import cat.devsofthecoast.teammanagementdemo.feature.commons.repository.TMDRepository

class GetQuestionUseCase(val appConfig: BaseConfig,
                         private val repository: TMDRepository) : UseCase<String, Question>(appConfig) {
    override fun run(input: String?, callback: Callback<Question>?) {
        try {
            repository.getQuestion(input!!) { success, error ->
                when {
                    error != null -> callback?.onError(error)
                    success == null -> callback?.onError(NullResponseFirebase())
                    else -> {
                        callback?.onSuccess(success)
                    }
                }
            }
        } catch (ex: Throwable) {
            callback?.onError(ex)
        }
    }

    class Executor(config: BaseConfig, builder: (UseCaseExecutor<String, Question>.() -> Unit)?) :
            UseCaseExecutor<String, Question>(config, builder)
}