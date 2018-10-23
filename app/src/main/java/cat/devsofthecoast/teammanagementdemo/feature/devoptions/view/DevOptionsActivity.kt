package cat.devsofthecoast.teammanagementdemo.feature.devoptions.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ScrollView
import cat.devsofthecoast.teammanagementdemo.R
import cat.devsofthecoast.teammanagementdemo.TMDApp
import cat.devsofthecoast.teammanagementdemo.core.mvp.config.BaseConfig
import cat.devsofthecoast.teammanagementdemo.core.mvp.ui.PresenterActivity
import cat.devsofthecoast.teammanagementdemo.feature.devoptions.DevOptionsContract
import cat.devsofthecoast.teammanagementdemo.feature.devoptions.presenter.DevOptionsPresenter
import kotlinx.android.synthetic.main.activity_dev_options.*
import java.text.SimpleDateFormat
import java.util.*


class DevOptionsActivity : PresenterActivity<DevOptionsContract.Presenter, DevOptionsContract.View>(), DevOptionsContract.View {


    private val appConfig: BaseConfig by lazy {
        (application as TMDApp).getConfig()
    }

    override val presenter: DevOptionsContract.Presenter by lazy {
        DevOptionsPresenter(appConfig)
    }

    val runnable = Runnable { scvLogs.fullScroll(ScrollView.FOCUS_DOWN) }
    val formatHMS = SimpleDateFormat("hh:mm:ss", Locale.US)

    companion object {
        fun newIntent(context: Context): Intent {
            val intent = Intent(context, DevOptionsActivity::class.java)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dev_options)

        btnFillDatabase.setOnClickListener {
            presenter.fillDatabase()
        }
    }

    private fun postLog(message: String) {
        val dateformat: String = formatHMS.format(Calendar.getInstance().timeInMillis)
        tvLogs.append("$dateformat: $message \n")
        notifyChangesLogs()
    }

    private fun notifyChangesLogs() {
        scvLogs.post(runnable)
    }

    override fun databaseFilled() {
        Log.d(this.javaClass.name, "Database filled correctly")
        postLog(this.databaseFilled().javaClass.name)
    }
}
