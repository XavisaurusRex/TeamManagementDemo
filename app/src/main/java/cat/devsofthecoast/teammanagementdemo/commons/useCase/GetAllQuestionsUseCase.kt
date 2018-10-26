package cat.devsofthecoast.teammanagementdemo.commons.useCase

import cat.devsofthecoast.teammanagementdemo.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.core.mvp.useCase.Callback
import cat.devsofthecoast.teammanagementdemo.core.mvp.useCase.UseCase
import cat.devsofthecoast.teammanagementdemo.core.mvp.useCase.UseCaseExecutor
import cat.devsofthecoast.teammanagementdemo.commons.exceptions.EmptyListResponseFirebase
import cat.devsofthecoast.teammanagementdemo.commons.exceptions.NullResponseFirebase
import cat.devsofthecoast.teammanagementdemo.commons.models.questions.Question
import cat.devsofthecoast.teammanagementdemo.commons.repository.TMDRepository

class GetAllQuestionsUseCase(val appConfig: BaseConfig,
                             private val repository: TMDRepository) : UseCase<Void?, ArrayList<Question>>(appConfig) {


    override fun run(input: Void?, callback: Callback<ArrayList<Question>>?) {
        try {
            repository.getAllQuestions { success, error ->
                when {
                    error != null -> callback?.onError(error)
                    success == null -> callback?.onError(NullResponseFirebase())
                    success.isEmpty() -> callback?.onError(EmptyListResponseFirebase())
                    else -> {
                        callback?.onSuccess(success)
                    }
                }
            }
        } catch (ex: Throwable) {
            callback?.onError(ex)
        }
    }

    class Executor(config: BaseConfig, builder: (UseCaseExecutor<Void?, ArrayList<Question>>.() -> Unit)?) :
            UseCaseExecutor<Void?, ArrayList<Question>>(config, builder)
}