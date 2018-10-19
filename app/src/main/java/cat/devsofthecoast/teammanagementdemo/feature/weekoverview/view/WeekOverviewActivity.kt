package cat.devsofthecoast.teammanagementdemo.feature.weekoverview.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import cat.devsofthecoast.teammanagementdemo.R
import cat.devsofthecoast.teammanagementdemo.R.id.*
import cat.devsofthecoast.teammanagementdemo.TMDApp
import cat.devsofthecoast.teammanagementdemo.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.core.mvp.ui.PresenterActivity
import cat.devsofthecoast.teammanagementdemo.feature.teamslist.view.TeamsListActivity
import cat.devsofthecoast.teammanagementdemo.feature.weekoverview.WeekOverviewContract
import cat.devsofthecoast.teammanagementdemo.feature.weekoverview.presenter.WeekOverviewPresenter

class WeekOverviewActivity : PresenterActivity<WeekOverviewContract.Presenter, WeekOverviewContract.View>(), WeekOverviewContract.View {

    private val appConfig: BaseConfig by lazy {
        (application as TMDApp).getConfig()
    }

    override val presenter: WeekOverviewContract.Presenter by lazy {
        WeekOverviewPresenter(appConfig)
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

    }

    override fun onBackPressed() {}

}
