package cat.devsofthecoast.teammanagementdemo.feature.activities.selectteam.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import cat.devsofthecoast.teammanagementdemo.R
import cat.devsofthecoast.teammanagementdemo.TMDApp
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.ui.PresenterActivity
import cat.devsofthecoast.teammanagementdemo.commons.models.Team
import cat.devsofthecoast.teammanagementdemo.commons.utilities.toast
import cat.devsofthecoast.teammanagementdemo.feature.activities.headactivity.view.HeadActivity
import cat.devsofthecoast.teammanagementdemo.feature.activities.login.view.LoginActivity
import cat.devsofthecoast.teammanagementdemo.feature.activities.selectteam.SelectTeamContract
import cat.devsofthecoast.teammanagementdemo.feature.activities.selectteam.adapter.impl.TeamsAdapterImpl
import kotlinx.android.synthetic.main.activity_select_team.*
import kotlinx.android.synthetic.main.fragment_survey.*

class SelectTeamActivity : PresenterActivity<SelectTeamContract.Presenter, SelectTeamContract.View>(), SelectTeamContract.View {

    override val presenter: SelectTeamContract.Presenter by lazy {
        (application as TMDApp).presenterModule.selectTeamPresenter
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, SelectTeamActivity::class.java)
        }
    }


    private val teamsAdapter: TeamsAdapterImpl by lazy {
        TeamsAdapterImpl(this, arrayListOf())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_team)

        configureInteractions()
        presenter.getTeams()
    }

    private fun configureInteractions() {
        rcyTeams.layoutManager = LinearLayoutManager(this)
        rcyTeams.adapter = TeamsAdapterImpl(this, arrayListOf())

        btnNext.setOnClickListener {
            startActivity(
                    HeadActivity.newIntent(this@SelectTeamActivity)
            )
        }

        btnCancel.setOnClickListener {
            startActivity(
                    LoginActivity.newIntent(this@SelectTeamActivity, true, true)
            )
        }
    }

    override fun onGetTeamsSuccess(list: List<Team>) {
        teamsAdapter.add(list)
    }

    override fun onGetTeamsError(throwable: Throwable) {
        toast("ERROR -> ${throwable.message}")
    }
}
