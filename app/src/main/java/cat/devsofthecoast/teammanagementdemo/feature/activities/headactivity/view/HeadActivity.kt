package cat.devsofthecoast.teammanagementdemo.feature.activities.headactivity.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import cat.devsofthecoast.teammanagementdemo.BuildConfig
import cat.devsofthecoast.teammanagementdemo.R
import cat.devsofthecoast.teammanagementdemo.TMDApp
import cat.devsofthecoast.teammanagementdemo.commons.core.mvp.ui.PresenterActivity
import cat.devsofthecoast.teammanagementdemo.feature.activities.headactivity.HeadContract
import cat.devsofthecoast.teammanagementdemo.feature.activities.login.view.LoginActivity
import cat.devsofthecoast.teammanagementdemo.feature.fragments.devoptions.view.DevOptionsFragment
import cat.devsofthecoast.teammanagementdemo.feature.fragments.surveyfragment.view.SurveyFragment
import cat.devsofthecoast.teammanagementdemo.feature.fragments.weekpreview.view.WeekPreviewFragment
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_header.*
import kotlinx.android.synthetic.main.content_navigation_drawer.*
import kotlinx.android.synthetic.main.toolbar.*
import androidx.core.content.IntentCompat
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK




class HeadActivity : PresenterActivity<HeadContract.Presenter, HeadContract.View>(), HeadContract.View {
    override val presenter: HeadContract.Presenter by lazy {
        (application as TMDApp).presenterModule.headContract
    }


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

        supportActionBar?.setHomeAsUpIndicator(android.R.drawable.ic_menu_help)
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
    }

    private fun startFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.flContent, fragment)
                .commit()
    }

    // Menu icons are inflated just as they were with actionbar
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_weekoverview, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                //todo must show and hide drawer, not only show
                mDrawer.openDrawer(GravityCompat.START)
                return true
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


}
