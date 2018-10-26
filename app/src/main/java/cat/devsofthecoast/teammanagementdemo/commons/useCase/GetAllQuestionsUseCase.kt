package cat.devsofthecoast.teammanagementdemo.commons.useCase

import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.useCase.Callback
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.useCase.UseCase
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.useCase.UseCaseExecutor
import cat.devsofthecoast.teammanagementdemo.commons.exceptions.EmptyListResponseFirebase
import cat.devsofthecoast.teammanagementdemo.commons.models.questions.Question
import cat.devsofthecoast.teammanagementdemo.commons.repository.questions.QuestionsRepository
import cat.devsofthecoast.teammanagementdemo.commons.services.ServiceCallback

class GetAllQuestionsUseCase(val appConfig: BaseConfig,
                             private val repository: QuestionsRepository) : UseCase<Void?, ArrayList<Question>>(appConfig) {


    override fun run(input: Void?, callback: Callback<ArrayList<Question>>?) {
        try {
            repository.getAllQuestions(object : ServiceCallback<ArrayList<Question>?> {
                override fun onSuccess(value: ArrayList<Question>?) {
                    if (value!!.isNotEmpty()) {
                        callback?.onSuccess(value)
                    } else {
                        throw EmptyListResponseFirebase()
                    }
                }

                override fun <E : Throwable> onError(error: E) {
                    callback?.onError(error)
                }
            })
        } catch (ex: Throwable) {
            callback?.onError(ex)
        }
    }

    class Executor(config: BaseConfig, builder: (UseCaseExecutor<Void?, ArrayList<Question>>.() -> Unit)?) :
            UseCaseExecutor<Void?, ArrayList<Question>>(config, builder)
}