package cat.devsofthecoast.teammanagementdemo.feature.weekoverview

import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.presenter.BasePresenter
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.presenter.BaseView
import cat.devsofthecoast.teammanagementdemo.commons.models.questions.Question

interface WeekOverviewContract {
    interface View : BaseView {
        fun configureInteractions()

        fun onGetQuestionSuccess(question: Question)
        fun onGetQuestionError(error: Throwable)
    }

    abstract class Presenter : BasePresenter<View>() {
        abstract fun getQuestion(key: String)
    }
}