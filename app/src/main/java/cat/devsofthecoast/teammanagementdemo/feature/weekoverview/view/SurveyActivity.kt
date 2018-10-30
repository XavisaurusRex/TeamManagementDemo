package cat.devsofthecoast.teammanagementdemo.feature.weekoverview.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import cat.devsofthecoast.teammanagementdemo.BuildConfig
import cat.devsofthecoast.teammanagementdemo.R
import cat.devsofthecoast.teammanagementdemo.R.id.*
import cat.devsofthecoast.teammanagementdemo.TMDApp
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.ui.PresenterActivity
import cat.devsofthecoast.teammanagementdemo.commons.models.questions.Question
import cat.devsofthecoast.teammanagementdemo.commons.utilities.toast
import cat.devsofthecoast.teammanagementdemo.feature.devoptions.view.DevOptionsActivity
import cat.devsofthecoast.teammanagementdemo.feature.teamslist.view.TeamsListActivity
import cat.devsofthecoast.teammanagementdemo.feature.weekoverview.SurveyContract
import cat.devsofthecoast.teammanagementdemo.feature.weekoverview.adapter.impl.QuestionsAdapterImpl
import kotlinx.android.synthetic.main.activity_daily_overview.*


class SurveyActivity : PresenterActivity<SurveyContract.Presenter, SurveyContract.View>(), SurveyContract.View {

    override val presenter: SurveyContract.Presenter by lazy {
        (application as TMDApp).presenterModule.surveyPresenter
    }

    val questionsAdapter: QuestionsAdapterImpl by lazy {
        QuestionsAdapterImpl(this, arrayListOf())
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, SurveyActivity::class.java)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        MenuInflater(this).inflate(R.menu.menu_weekoverview, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            mnuProfileData -> startActivity(TeamsListActivity.newIntent(this))
            mnuDailyEntries -> startActivity(TeamsListActivity.newIntent(this))
            mnuManageSurvey -> startActivity(TeamsListActivity.newIntent(this))
            mnuOther -> startActivity(TeamsListActivity.newIntent(this))
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_overview)
        title = null

        configureInteractions()
    }

    override fun configureInteractions() {
        if (BuildConfig.DEBUG) {
            btnDevOpt.setOnLongClickListener {
                startActivity(DevOptionsActivity.newIntent(this@SurveyActivity))
                true
            }
        }

        btnGetQuestions.setOnClickListener {
            presenter.getAllQuestions()
        }

        btnClearAll.setOnClickListener {
            if (questionsAdapter.size() > 0) {
                questionsAdapter.removeAll()
            }
        }

        rcySurvey.layoutManager = LinearLayoutManager(this)
        rcySurvey.adapter = questionsAdapter
    }

    override fun onBackPressed() {}

    override fun onGetQuestionSuccess(question: Question) {
        toast("question received")
        questionsAdapter.add(question)

    }

    override fun onGetQuestionError(error: Throwable) {
        toast("cannot get question: ${error.message}")
    }

    override fun onGetAllQuestionsSuccess(questions: ArrayList<Question>) {
        questionsAdapter.add(questions)
    }

    override fun onGetAllQuestionsError(error: Throwable) {
        toast("cannot get questions: ${error.message}")
    }
}
