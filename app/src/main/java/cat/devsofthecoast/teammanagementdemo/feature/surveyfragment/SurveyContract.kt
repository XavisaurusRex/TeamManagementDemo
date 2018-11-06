package cat.devsofthecoast.teammanagementdemo.feature.surveyfragment

import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.presenter.BasePresenter
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.presenter.BaseView
import cat.devsofthecoast.teammanagementdemo.commons.models.questions.Question

interface SurveyContract {
    interface View : BaseView {
        fun configureInteractions()

        fun onGetQuestionSuccess(question: Question)
        fun onGetQuestionError(error: Throwable)

        fun onGetAllQuestionsSuccess(questions: ArrayList<Question>)
        fun onGetAllQuestionsError(error: Throwable)
    }

    abstract class Presenter : BasePresenter<View>() {
        abstract fun getQuestion(key: String)
        abstract fun getAllQuestions()
    }
}