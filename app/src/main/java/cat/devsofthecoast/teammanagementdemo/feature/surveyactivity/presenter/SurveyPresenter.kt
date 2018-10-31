package cat.devsofthecoast.teammanagementdemo.feature.surveyactivity.presenter

import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.commons.useCase.GetAllQuestionsUseCase
import cat.devsofthecoast.teammanagementdemo.commons.useCase.GetQuestionUseCase
import cat.devsofthecoast.teammanagementdemo.feature.surveyactivity.SurveyContract

class SurveyPresenter(
        private val appConfig: BaseConfig,
        private val getQuestionUseCase: GetQuestionUseCase,
        private val getAllQuestionsUseCase: GetAllQuestionsUseCase)
    : SurveyContract.Presenter() {

    override fun getQuestion(key: String) {
        GetQuestionUseCase.Executor(appConfig) {
            useCase = getQuestionUseCase
            onSuccess = {
                view?.onGetQuestionSuccess(it)
            }
            onError = {
                view?.onGetQuestionError(it)
            }
        }.execute(key)
    }

    override fun getAllQuestions() {
        GetAllQuestionsUseCase.Executor(appConfig) {
            useCase = getAllQuestionsUseCase
            onSuccess = {
                view?.onGetAllQuestionsSuccess(it)
            }
            onError = {
                view?.onGetAllQuestionsError(it)
            }
        }.execute(null)
    }
}