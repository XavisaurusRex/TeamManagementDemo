package cat.devsofthecoast.teammanagementdemo.feature.devoptions

import cat.devsofthecoast.teammanagementdemo.core.mvp.presenter.BasePresenter
import cat.devsofthecoast.teammanagementdemo.core.mvp.presenter.BaseView
import cat.devsofthecoast.teammanagementdemo.commons.models.questions.Question

interface DevOptionsContract {
    interface View : BaseView {
        fun onDatabaseFilledSuccess()
        fun onDatabaseFilledError(ex: Throwable)

        fun onGetAllQuestionsSuccess(questions: ArrayList<Question>)
        fun onGetAllQuestionsError(ex: Throwable)

        fun onGetQuestionsSuccess(question: Question)
        fun onGetQuestionsError(ex: Throwable)
    }

    abstract class Presenter : BasePresenter<View>() {
        abstract fun fillDatabase()
        abstract fun getAllQuestions()
        abstract fun getSingleQuestion(key: String)
    }
}