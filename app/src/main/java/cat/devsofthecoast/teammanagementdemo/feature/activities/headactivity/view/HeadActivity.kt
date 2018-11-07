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
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.ui.PresenterActivity
import cat.devsofthecoast.teammanagementdemo.feature.activities.headactivity.HeadContract
import cat.devsofthecoast.teammanagementdemo.feature.activities.headactivity.controllers.TMDActionBarDrawerToggle
import cat.devsofthecoast.teammanagementdemo.feature.activities.login.view.LoginActivity
import cat.devsofthecoast.teammanagementdemo.feature.fragments.devoptions.view.DevOptionsFragment
import cat.devsofthecoast.teammanagementdemo.feature.fragments.surveyfragment.view.SurveyFragment
import cat.devsofthecoast.teammanagementdemo.feature.fragments.weekpreview.view.WeekPreviewFragment
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_header.*
import kotlinx.android.synthetic.main.content_navigation_drawer.*
import kotlinx.android.synthetic.main.toolbar.*


class HeadActivity : PresenterActivity<HeadContract.Presenter, HeadContract.View>(), HeadContract.View {

    override val presenter: HeadContract.Presenter by lazy {
        (application as TMDApp).presenterModule.headContract
    }

    private var toggle: TMDActionBarDrawerToggle? = null

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, HeadActivity::class.java)
        }
    }

    override fun onBackPressed() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_header)

        setSupportActionBar(toolbar)

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        configureInteractions()

        startFragment(WeekPreviewFragment())
    }

    private fun configureInteractions() {
        btnSurveyActivity.setOnClickListener {
            startFragment(SurveyFragment())
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
        supportFragmentManager.beginTransaction()
                .replace(R.id.flContent, fragment)
                .commit()
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
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_left_arrow)
    }

    override fun onDrawerClosed() {
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
    }
}
