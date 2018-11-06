package cat.devsofthecoast.teammanagementdemo.feature.fragments.devoptions.presenter

import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.commons.useCase.ClearDatabseChildUseCase
import cat.devsofthecoast.teammanagementdemo.commons.useCase.demo.FillDatabaseUseCase
import cat.devsofthecoast.teammanagementdemo.commons.useCase.questions.GetAllQuestionsUseCase
import cat.devsofthecoast.teammanagementdemo.commons.useCase.questions.GetQuestionUseCase
import cat.devsofthecoast.teammanagementdemo.feature.fragments.devoptions.DevOptionsContract

class DevOptionsPresenter(
        private val appConfig: BaseConfig,
        private val fillDatabaseUseCase: FillDatabaseUseCase,
        private val getAllQuestionsUseCase: GetAllQuestionsUseCase,
        private val getQuestionUseCase: GetQuestionUseCase,
        private val clearDatabseChildUseCase: ClearDatabseChildUseCase)
    : DevOptionsContract.Presenter() {

    override fun clearDatabase(child: String) {
        ClearDatabseChildUseCase.Executor(appConfig) {
            useCase = clearDatabseChildUseCase
            onSuccess = {
                view?.onClearDatabaseChildSuccess(it)
            }
            onError = {
                view?.onClearDatabaseChildError(it)
            }
        }.execute(child)
    }

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