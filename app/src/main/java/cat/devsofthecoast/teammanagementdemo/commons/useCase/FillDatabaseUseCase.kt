package cat.devsofthecoast.teammanagementdemo.commons.useCase

import android.content.Context
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.useCase.Callback
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.useCase.UseCase
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.useCase.UseCaseExecutor
import cat.devsofthecoast.teammanagementdemo.commons.models.questions.Question
import cat.devsofthecoast.teammanagementdemo.commons.repository.questions.QuestionsRepository
import cat.devsofthecoast.teammanagementdemo.commons.services.ServiceCallback
import cat.devsofthecoast.teammanagementdemo.commons.services.dummiecreator.DummyCreator
import cat.devsofthecoast.teammanagementdemo.commons.utilities.readJsonFile
import org.json.JSONObject

class FillDatabaseUseCase(val appConfig: BaseConfig,
                          val context: Context,
                          private val repository: QuestionsRepository) : UseCase<Void?, Boolean>(appConfig) {
    private val DEMO_RANDOM_INFO_JSON = ""
    override fun run(input: Void?, callback: Callback<Boolean>?) {
        try {
            val jsonObject = JSONObject(context.readJsonFile("cat/devsofthecoast/teammanagementdemo/commons/services/dummiecreator/random.json"))

            val questions: List<Question> = DummyCreator.createQuestions(jsonObject.getJSONArray("question_statments"), jsonObject.getJSONArray("questionoptions"))
            repository.setQuestions(, object : ServiceCallback<Boolean> {
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