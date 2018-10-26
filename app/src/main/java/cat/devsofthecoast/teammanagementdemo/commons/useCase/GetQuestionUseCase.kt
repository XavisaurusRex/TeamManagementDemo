package cat.devsofthecoast.teammanagementdemo.commons.useCase

import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.useCase.Callback
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.useCase.UseCase
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.useCase.UseCaseExecutor
import cat.devsofthecoast.teammanagementdemo.commons.models.questions.Question
import cat.devsofthecoast.teammanagementdemo.commons.repository.questions.QuestionsRepository
import cat.devsofthecoast.teammanagementdemo.commons.services.ServiceCallback

class GetQuestionUseCase(val appConfig: BaseConfig,
                         private val repository: QuestionsRepository) : UseCase<String, Question>(appConfig) {
    override fun run(input: String?, callback: Callback<Question>?) {
        try {
            repository.getQuestion(input!!, object : ServiceCallback<Question?> {
                override fun onSuccess(value: Question?) {
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

    class Executor(config: BaseConfig, builder: (UseCaseExecutor<String, Question>.() -> Unit)?) :
            UseCaseExecutor<String, Question>(config, builder)
}