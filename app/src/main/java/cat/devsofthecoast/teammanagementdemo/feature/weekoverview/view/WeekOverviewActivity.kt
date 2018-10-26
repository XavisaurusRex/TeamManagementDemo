package cat.devsofthecoast.teammanagementdemo.feature.weekoverview.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
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
import cat.devsofthecoast.teammanagementdemo.feature.weekoverview.WeekOverviewContract
import kotlinx.android.synthetic.main.activity_daily_overview.*


class WeekOverviewActivity : PresenterActivity<WeekOverviewContract.Presenter, WeekOverviewContract.View>(), WeekOverviewContract.View {

    override val presenter: WeekOverviewContract.Presenter by lazy {
        (application as TMDApp).presenterModule.weekOverviewPresenter
    }

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, WeekOverviewActivity::class.java)
            return intent
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
        startPetitions()
    }

    private fun startPetitions() {

    }

    override fun configureInteractions() {
        if (BuildConfig.DEBUG) {
            btnDevOpt.setOnLongClickListener {
                startActivity(DevOptionsActivity.newIntent(this@WeekOverviewActivity))
                true
            }
        }

        btnRetry.setOnClickListener {
            presenter.getQuestion("-LPh4y9Tj47h1wa7gMke")
        }

    }

    override fun onBackPressed() {}

    override fun onGetQuestionSuccess(question: Question) {
        toast("question received")
    }

    override fun onGetQuestionError(error: Throwable) {
        toast("cannot get question: ${error.message}")
    }
}
