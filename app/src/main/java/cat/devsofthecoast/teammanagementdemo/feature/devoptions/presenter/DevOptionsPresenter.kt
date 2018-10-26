package cat.devsofthecoast.teammanagementdemo.feature.devoptions.presenter

import cat.devsofthecoast.teammanagementdemo.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.feature.devoptions.DevOptionsContract
import cat.devsofthecoast.teammanagementdemo.commons.useCase.FillDatabaseUseCase
import cat.devsofthecoast.teammanagementdemo.commons.useCase.GetAllQuestionsUseCase
import cat.devsofthecoast.teammanagementdemo.commons.useCase.GetQuestionUseCase

class DevOptionsPresenter(
        private val appConfig: BaseConfig,
        private val fillDatabaseUseCase: FillDatabaseUseCase,
        private val getAllQuestionsUseCase: GetAllQuestionsUseCase,
        private val getQuestionUseCase: GetQuestionUseCase)
    : DevOptionsContract.Presenter() {

    override fun fillDatabase() {
        FillDatabaseUseCase.Executor(appConfig) {
            useCase = fillDatabaseUseCase
            onSuccess = {
                view?.onDatabaseFilledSuccess()
            }
            onError = {
                view?.onDatabaseFilledError(it)
            }
        }.execute(null)
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

    override fun getSingleQuestion(key: String) {
        GetQuestionUseCase.Executor(appConfig) {
            useCase = getQuestionUseCase
            onSuccess = {
                view?.onGetQuestionsSuccess(it)
            }
            onError = {
                view?.onGetQuestionsError(it)
            }
        }.execute(key)
    }
}