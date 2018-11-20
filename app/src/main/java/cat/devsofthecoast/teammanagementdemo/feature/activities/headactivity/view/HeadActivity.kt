package cat.devsofthecoast.teammanagementdemo.feature.activities.headactivity.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import cat.devsofthecoast.teammanagementdemo.BuildConfig
import cat.devsofthecoast.teammanagementdemo.R
import cat.devsofthecoast.teammanagementdemo.TMDApp
import cat.devsofthecoast.teammanagementdemo.commons.controllers.dialog.TMDDialog
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.ui.PresenterActivity
import cat.devsofthecoast.teammanagementdemo.commons.utilities.toast
import cat.devsofthecoast.teammanagementdemo.commons.models.Team
import cat.devsofthecoast.teammanagementdemo.commons.models.users.Trainer
import cat.devsofthecoast.teammanagementdemo.feature.activities.headactivity.HeadContract
import cat.devsofthecoast.teammanagementdemo.feature.activities.headactivity.controllers.TMDActionBarDrawerToggle
import cat.devsofthecoast.teammanagementdemo.feature.activities.login.view.LoginActivity
import cat.devsofthecoast.teammanagementdemo.feature.fragments.dailyentries.view.DailyEntriesFragment
import cat.devsofthecoast.teammanagementdemo.feature.fragments.devoptions.view.DevOptionsFragment
import cat.devsofthecoast.teammanagementdemo.feature.fragments.survey.view.SurveyFragment
import cat.devsofthecoast.teammanagementdemo.feature.fragments.trainerprofile.view.TrainerProfileFragment
import cat.devsofthecoast.teammanagementdemo.feature.fragments.weekpreview.view.WeekPreviewFragment
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_header.*
import kotlinx.android.synthetic.main.content_navigation_drawer.*
import kotlinx.android.synthetic.main.toolbar.*


class HeadActivity : PresenterActivity<HeadContract.Presenter, HeadContract.View>(), HeadContract.View {

    private val NAVIGATOR_CLOSED:Int = R.drawable.ic_menu
    private val NAVIGATOR_OPEN:Int = R.drawable.ic_left_arrow

    override val presenter: HeadContract.Presenter by lazy {
        (application as TMDApp).presenterModule.headPresenter
    }

    var loggedTrainer: Trainer? = null

    private var toggle: TMDActionBarDrawerToggle? = null

    companion object {
        val LOGGED_TRAINER = "loggedTrainer"

        fun newIntent(context: Context, trainer: Trainer): Intent {
            val intent = Intent(context, HeadActivity::class.java)
            intent.putExtra(LOGGED_TRAINER, trainer)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_header)

        setSupportActionBar(toolbar)

        supportActionBar?.setHomeAsUpIndicator(NAVIGATOR_CLOSED)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        getIntentExtras()
        configureInteractions()

        startFragment(WeekPreviewFragment())

        presenter.getLoggedTrainer()
    }

    private fun getIntentExtras() {

    }

    private fun configureInteractions() {
        ivProfile.setOnClickListener {
            startFragment(TrainerProfileFragment.newInstance(loggedTrainer!!))
        }

        btnSurveyActivity.setOnClickListener {
            startFragment(SurveyFragment())
        }

        btnTeamInfo.setOnClickListener {
            toast("not implemented fragment")
        }

        btnDailyEntries.setOnClickListener {
            startFragment(DailyEntriesFragment())
        }

        if (BuildConfig.DEBUG) {
            btnDevOptions.visibility = View.VISIBLE
            btnDevOptions.setOnClickListener {
                startFragment(DevOptionsFragment())
            }
        }

        configureNavigationView()
    }

    private fun configureNavigationView() {
        toggle = TMDActionBarDrawerToggle(this, tmdDrawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        toggle!!.setOnDrawerStateChange(tmdDrawerLayout)
        tmdDrawerLayout.addDrawerListener(toggle!!)
        tmdDrawerLayout.setOnChangeDrawerStateListener(this)
    }

    private fun startFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.flContent, fragment)
                .addToBackStack(null)
                .commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            val tmdDialog: TMDDialog = TMDDialog(this, llHeaderParent)
            tmdDialog.title = "You are leaving app"
            tmdDialog.description = "Are you sure want to exit? \n "
            tmdDialog.btnAceptText = "Exit"
            tmdDialog.btnCancelText = "Stay Here"

            tmdDialog.setTMDDialogListener(object : TMDDialog.TMDDialogListener {
                override fun onAcept() {
                    finishAffinity()
                    finish()
                }

                override fun onCancel() {}
                override fun onDismiss() {}
            })

            tmdDialog.show()
        } else {
            supportFragmentManager.popBackStackImmediate()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_weekoverview, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                tmdDrawerLayout.toggleState()
            }
            R.id.mnuLogout -> {
                logout()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun logout() {
        FirebaseAuth.getInstance().signOut()
        startActivity(LoginActivity.newIntent(this@HeadActivity, true, true))
        finishAffinity()
    }

    override fun onDrawerOpened() {
        supportActionBar?.setHomeAsUpIndicator(NAVIGATOR_OPEN)
    }

    override fun onDrawerClosed() {
        supportActionBar?.setHomeAsUpIndicator(NAVIGATOR_CLOSED)
    }

    override fun onGetTeamSuccess(team: Team) {
        btnTeamInfo.text = team.name
    }

    override fun onGetTeamError(throwable: Throwable) {
        toast("error getting team from firebase ${throwable.message}")
    }

    override fun onGetLoggedTrainerSuccess(trainer: Trainer) {
        loggedTrainer = trainer
        tvProfileDesc.text = loggedTrainer?.email
        presenter.getTeam(trainer.team!!)
    }

    override fun onGetLoggedTrainerError(throwable: Throwable) {
        toast("error getting trainer from firebase ${throwable.message}")
    }
}
