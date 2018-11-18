package cat.devsofthecoast.teammanagementdemo.feature.fragments.survey.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import cat.devsofthecoast.teammanagementdemo.R
import cat.devsofthecoast.teammanagementdemo.TMDApp
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.ui.PresenterFragment
import cat.devsofthecoast.teammanagementdemo.commons.models.questions.Question
import cat.devsofthecoast.teammanagementdemo.commons.utilities.toast
import cat.devsofthecoast.teammanagementdemo.feature.fragments.survey.SurveyContract
import cat.devsofthecoast.teammanagementdemo.feature.fragments.survey.adapter.impl.QuestionsAdapter
import kotlinx.android.synthetic.main.fragment_survey.*


class SurveyFragment : PresenterFragment<SurveyContract.Presenter, SurveyContract.View>(), SurveyContract.View {

    override val presenter: SurveyContract.Presenter by lazy {
        (activity!!.application as TMDApp).presenterModule.surveyPresenter
    }

    private val questionsAdapter: QuestionsAdapter by lazy {
        QuestionsAdapter(activity!!, arrayListOf())
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, SurveyFragment::class.java)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_survey, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureInteractions()
    }

    override fun configureInteractions() {
        btnGetQuestions.setOnClickListener {
            presenter.getAllQuestions()
        }

        btnClearAll.setOnClickListener {
            if (questionsAdapter.size() > 0) {
                questionsAdapter.removeAll()
            }
        }

        rcySurvey.layoutManager = LinearLayoutManager(activity)
        rcySurvey.adapter = questionsAdapter
    }

    override fun onGetQuestionSuccess(question: Question) {
        activity?.toast("question received")
        questionsAdapter.add(question)
    }

    override fun onGetQuestionError(error: Throwable) {
        activity?.toast("cannot get question: ${error.message}")
    }

    override fun onGetAllQuestionsSuccess(questions: ArrayList<Question>) {
        questionsAdapter.add(questions)
    }

    override fun onGetAllQuestionsError(error: Throwable) {
        activity?.toast("cannot get questions: ${error.message}")
    }
}
