package cat.devsofthecoast.teammanagementdemo.feature.activities.selectteam.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import cat.devsofthecoast.teammanagementdemo.R
import cat.devsofthecoast.teammanagementdemo.TMDApp
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.ui.PresenterActivity
import cat.devsofthecoast.teammanagementdemo.commons.models.Team
import cat.devsofthecoast.teammanagementdemo.commons.models.users.Trainer
import cat.devsofthecoast.teammanagementdemo.commons.utilities.toast
import cat.devsofthecoast.teammanagementdemo.feature.activities.headactivity.view.HeadActivity
import cat.devsofthecoast.teammanagementdemo.feature.activities.login.view.LoginActivity
import cat.devsofthecoast.teammanagementdemo.feature.activities.selectteam.SelectTeamContract
import cat.devsofthecoast.teammanagementdemo.feature.activities.selectteam.adapter.impl.TeamsAdapterImpl
import kotlinx.android.synthetic.main.activity_select_team.*

class SelectTeamActivity : PresenterActivity<SelectTeamContract.Presenter, SelectTeamContract.View>(), SelectTeamContract.View {

    override val presenter: SelectTeamContract.Presenter by lazy {
        (application as TMDApp).presenterModule.selectTeamPresenter
    }

    companion object {
        val LOGGED_TRAINER = "loggedTrainer"

        fun newIntent(context: Context, trainer: Trainer): Intent {
            val intent = Intent(context, SelectTeamActivity::class.java)
            intent.putExtra(LOGGED_TRAINER, trainer)
            return intent
        }
    }


    private val teamsAdapter: TeamsAdapterImpl by lazy {
        TeamsAdapterImpl(this, arrayListOf())
    }

    private var loggedTrainer: Trainer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_team)
        if (intent.hasExtra(LOGGED_TRAINER)) {
            loggedTrainer = intent.getParcelableExtra(LOGGED_TRAINER)
        } else {
            //todo gestionar excepcion
        }

        configureInteractions()
        presenter.getTeams()
    }

    private fun configureInteractions() {
        rcyTeams.layoutManager = LinearLayoutManager(this)
        rcyTeams.adapter = teamsAdapter

        btnNext.setOnClickListener {
            val team = teamsAdapter.getSelectedTeam()
            if (team != null) {
                presenter.associateTeamToTrainer(team, loggedTrainer!!)
            } else {
                toast("select Team")
            }
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

    override fun onLinkTeamTrainerSuccess() {
        startActivity(
                HeadActivity.newIntent(this@SelectTeamActivity, loggedTrainer!!)
        )
    }

    override fun onLinkTeamTrainerError(it: Throwable) {
        toast("Error has ocurred ${it.message}")
    }
}
